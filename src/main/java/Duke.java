import java.util.Scanner;
import java.io.File;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    public void run() {
        ui.showWelcomeMessage();

        while (true) {
            String userInput = ui.getUserInput();
            Command command = Parser.parseCommand(userInput);
            String description = Parser.parseDescription(userInput);
            switch (command) {
                case EXIT:
                    storage.save(tasks, "tasks.txt");
                    ui.closeScanner();
                    ui.showExitMessage();
                    return;
                case LIST:
                    ui.showTaskList(tasks);
                    break;
                case UNMARK:
                    try {
                        int taskNumber = Integer.parseInt(description) - 1;
                        if (taskNumber >= 0 && taskNumber < tasks.size()) {
                            tasks.unmarkTask(taskNumber);
                        } else {
                            ui.showError("Task number out of range.");
                        }
                    } catch (NumberFormatException e) {
                        ui.showError("Invalid task number. Please provide a valid integer.");
                    }
                    break;
                case MARK:
                    try {
                        int taskNumber = Integer.parseInt(description) - 1;
                        if (taskNumber >= 0 && taskNumber < tasks.size()) {
                            tasks.markTaskAsDone(taskNumber);
                        } else {
                            ui.showError("Task number out of range.");
                        }
                    } catch (NumberFormatException e) {
                        ui.showError("Invalid task number. Please provide a valid integer.");
                    }
                    break;
                case TODO:
                    try {
                        if (description.isEmpty()) {
                            throw new EmptyTodoException();
                        }
                        Todo todo = new Todo(description, false);
                        tasks.addTask(todo);

                    } catch (EmptyTodoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case DEADLINE:

                    if (description.isEmpty()) {
                        try {
                            throw new EmptyDeadlineException();
                        } catch (EmptyDeadlineException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        // Find the index of the deadline separator "/"
                        int separatorIndex = description.indexOf('/');

                        if (separatorIndex != -1) { // Ensure the separator exists in the input
                            // Extract the task description and deadline

                            String descriptionString = description.substring(0, separatorIndex).trim();
                            String deadline = description.substring(separatorIndex + 4).trim();
                            String pattern = "\\d{4}/\\d{2}/\\d{2}";
                            Pattern datePattern = Pattern.compile(pattern);
                            Matcher matcher = datePattern.matcher(deadline);
                            if (matcher.find()) {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                                LocalDate localDateDeadline = LocalDate.parse(deadline, formatter);
                                Deadline deadlineTask = new Deadline(descriptionString, false, localDateDeadline);
                                tasks.addTask(deadlineTask);

                            } else {
                                System.out.println("Please input your deadline in YYYY/MM/DD format");
                            }
                        } else {
                            System.out.println("Invalid input format for deadline. Please input in the following format: <deadline> <description> /by <YYYY/MM/DD> ");
                        }
                    }
                    break;
                case EVENT:
                    if (description.isEmpty()) {
                        try {
                            throw new EmptyEventException();
                        } catch (EmptyEventException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        // Find the indices of the time separators
                        int fromIndex = description.indexOf("/from");
                        int toIndex = description.indexOf("/to");

                        if (fromIndex != -1 && toIndex != -1) {
                            // Extract the task description, startTime, and endTime
                            String descriptionString = description.substring(0, fromIndex).trim();
                            String startTime = description.substring(fromIndex + 5, toIndex).trim();
                            String endTime = description.substring(toIndex + 3).trim();

                            // Create a new Event object
                            Event eventTask = new Event(descriptionString, false, startTime, endTime);
                            tasks.addTask(eventTask);

                        } else {
                            System.out.println("Invalid input format for event command.");
                        }
                    }
                    break;
                case DELETE:
                    try {
                        int taskNumber = Integer.parseInt(description) - 1;
                        if (taskNumber >= 0 && taskNumber < tasks.size()) {
                            tasks.deleteTask(taskNumber);
                        } else {
                            ui.showError("Task number out of range.");
                        }
                    } catch (NumberFormatException e) {
                        ui.showError("Invalid task number. Please provide a valid integer.");
                    }
                    break;
                case INVALID:
                    ui.showError("Invalid command. Please try again.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}

