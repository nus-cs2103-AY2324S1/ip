import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;


public class Duke {
    private final String filePath = "./data/duke.txt";
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println("load error");
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.greeting();
        try {
            while (true) {
                String response = ui.getUserInput();

                // Use the parseCommandType method to determine the command type
                Parser.CommandType commandType = parser.parseCommandType(response);

                switch (commandType) {
                    case BYE:
                        ui.exit();
                        ui.closeScanner();
                        try {
                            storage.save(taskList.getTaskArrayList());
                        } catch (IOException ioException) {
                            System.out.println("save error");
                        }
                        return;
                    case LIST:
                        ui.showMessage("Here are the tasks in your list:");
                        taskList.listTasks();
                        continue;
                    case MARK:
                        int markId = Integer.parseInt(response.substring(5));
                        String markMessage = taskList.markTaskAsDone(markId);
                        ui.showMessage(markMessage);
                        continue;
                    case UNMARK:
                        int unmarkId = Integer.parseInt(response.substring(7));
                        String unmarkMessage = taskList.unmarkTask(unmarkId);
                        ui.showMessage(unmarkMessage);
                        continue;
                    case TODO:
                        String todoDescription = response.substring(5);
                        Task toDo = parser.addTodoTask(todoDescription);
                        String todoMessage = taskList.addTask(toDo);
                        ui.showMessage(todoMessage);
                        continue;
                    case EVENT:
                        String[] eventParts = response.split("/from|/to");
                        if (eventParts.length == 3) {
                            String eventDescription = eventParts[0].substring(6).trim();
                            String from = eventParts[1].trim();
                            String to = eventParts[2].trim();
                            Task event = parser.addEventTask(eventDescription, from, to);
                            String eventMessage = taskList.addTask(event);
                            ui.showMessage(eventMessage);
                        } else {
                            ui.showMessage("Invalid event format. Use 'event <description> /from <datetime> /to <datetime>'.");
                        }
                        continue;
                    case DEADLINE:
                        String[] deadlineParts = response.split("/by", 2);
                        if (deadlineParts.length == 2) {
                            String deadlineDescription = deadlineParts[0].substring(9).trim();
                            String dateTimeString = deadlineParts[1].trim();
                            Task deadline = parser.addDeadlineTask(deadlineDescription, dateTimeString);
                            String deadlineMessage = taskList.addTask(deadline);
                            ui.showMessage(deadlineMessage);
                        } else {
                            String message = "Invalid deadline format. Use 'deadline <description> /by <datetime>'.";
                            ui.showMessage(message);
                        }
                        continue;
                    case DELETE:
                        int deleteId = Integer.parseInt(response.substring(7));
                        String deleteMessage = taskList.deleteTask(deleteId);
                        ui.showMessage(deleteMessage);
                        continue;
                    case INVALID:
                        ui.showMessage("I'm sorry, I don't understand that command.");
                }
                try {
                    storage.save(taskList.getTaskArrayList());
                } catch (IOException ioException) {
                    System.out.println("save error");
                }
            }
        } catch (Exception e) {
            try {
                storage.save(taskList.getTaskArrayList());
            } catch (IOException ioException) {
                System.out.println("save error");
            }
        }

    }

    public static void main(String[] args) {
        Duke myBot = new Duke();
        myBot.run();
    }
}

