package chatty;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    protected Ui ui;
    protected Storage storage;
    protected TaskList tasks;
    private static final String FILE_PATH = "../data/Duke.txt";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
    }

    public void run() {
        Scanner scanner = ui.getScanner();
        boolean isRunning = true;

        int taskCount = tasks.size();
        System.out.println(ui.helloMessage());
        System.out.println(ui.lineBreak());

        while (isRunning) {
            try {
                String userInput = ui.getUserInput();
                System.out.println(ui.lineBreak());
                if (userInput.equals("bye")) {
                    storage.saveTasks(tasks);
                    System.out.println(ui.goodbyeMessage());
                    isRunning = false;
                } else if (userInput.equals("list")){
                    System.out.println(tasks.printTasks());
                } else if (userInput.startsWith("mark ")) {
                    System.out.println(ui.markTaskDone());
                    int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                    tasks.get(taskIndex).switchCheck();
                    System.out.println(tasks.get(taskIndex).toString());
                } else if (userInput.startsWith("unmark ")) {
                    System.out.println(ui.unmarkTaskDone());
                    int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    tasks.get(taskIndex).switchCheck();
                    System.out.println(tasks.get(taskIndex).toString());
                } else if (userInput.startsWith("todo ")) {
                    if (userInput.length() <= 5) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task newToDo = new Todo(userInput.substring(5));
                    System.out.println(ui.addTask());
                    tasks.add(new Todo(userInput));
                    taskCount++;
                    System.out.println(newToDo.toString());
                    System.out.println(tasks.printTaskCount());
                } else if (userInput.startsWith("deadline ")) {
                    System.out.println(ui.addTask());
                    String description = userInput.substring(9, userInput.indexOf("/by")).trim();
                    String by = userInput.substring(userInput.indexOf("/by") + 4).trim();
                    try {
                        Task newDeadline = new Deadline(description, by);
                        tasks.add(newDeadline);
                        taskCount++;
                        System.out.println(newDeadline.toString());
                        System.out.println(tasks.printTaskCount());
                    } catch (DateTimeParseException e) {
                        System.out.println(ui.dateTimeParseExceptionMessage());
                        //skip to next iteration of loop, which gets next line of user input
                        continue;
                    }
                } else if (userInput.startsWith("event ")) {
                    System.out.println(ui.addTask());
                    String description = userInput.substring(6, userInput.indexOf("/from")).trim();
                    String from = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to")).trim();
                    String to = userInput.substring(userInput.indexOf("/to") + 4).trim();
                    try {
                        Task newEvent = new Event(description, from, to);
                        tasks.add(newEvent);
                        taskCount++;
                        System.out.println(newEvent.toString());
                        System.out.println(tasks.printTaskCount());
                    } catch (DateTimeParseException e) {
                        System.out.println(ui.dateTimeParseExceptionMessage());
                        //skip to next iteration of loop, which gets next line of user input
                        continue;
                    }
                } else if (userInput.startsWith("delete ")) {
                    System.out.println(ui.removeTask());
                    int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    System.out.println(tasks.get(taskIndex));
                    tasks.remove(taskIndex);
                    taskCount--;
                    System.out.println(tasks.printTaskCount());
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                // Handle Duke-specific exceptions with meaningful error messages
                System.out.println(e.getMessage());
            }
            System.out.println(ui.lineBreak());
        }
        ui.closeScanner();
    }
    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }


}
