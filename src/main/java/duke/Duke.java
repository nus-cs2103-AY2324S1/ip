package duke;

import java.util.Scanner;

/**
 * Represents a chat bot that can keep track of tasks.
 */
public class Duke {
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a Duke object.
     * Catches DukeException if there is an error loading the file.
     * @param filePath Path to the file to be loaded.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program.
     */
    public void run() {
        // show introduction
        ui.showIntroduction();

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println();
            String input = sc.nextLine().trim();

            while (!input.equals("bye")) {
                handleTextInput(input);
                System.out.println();
                input = sc.nextLine().trim();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        ui.sendFarewell();
    }

    private void handleTextInput(String inputString) {
        ui.printDivider();
        // handle key logic here.
        String command = inputString.split(" ")[0];
        int taskIndex;

        switch (command) {
        case "list":
            ui.printTaskList(tasks.toString());
            break;
        case "mark":
            taskIndex = Integer.parseInt(inputString.split(" ")[1]) - 1;
            // tasks.get(taskIndex).markAsDone();
            tasks.markTaskAsDone(taskIndex);
            ui.markTaskAsDoneMessage(tasks.taskToString(taskIndex));
            break;
        case "unmark":
            taskIndex = Integer.parseInt(inputString.split(" ")[1]) - 1;
            tasks.unmarkTaskAsDone(taskIndex);
            ui.unmarkTaskAsDoneMessage(tasks.taskToString(taskIndex));
            break;
        case "todo":
            try {
                String taskName = inputString.substring(5);
                Task newTask = new ToDo(taskName);
                tasks.addToTaskList(newTask);
                ui.addTaskOutputText(newTask, tasks.size());
            } catch (StringIndexOutOfBoundsException e) {
                ui.showErrorMessage("\t☹ OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case "deadline":
            try {
                // stop before /by
                String taskName = inputString.substring(9, inputString.indexOf("/by") - 1);
                // get day
                String deadline = inputString.substring(inputString.indexOf("/by") + 4);
                try {

                    Task newTask = new Deadline(taskName, deadline);
                    tasks.addToTaskList(newTask);
                    ui.addTaskOutputText(newTask, tasks.size());
                } catch (java.time.format.DateTimeParseException e) {
                    // System.out.println("Error: " + e.getMessage());
                    ui.showErrorMessage("\tInvalid date format. Please use yyyy-mm-dd.");
                }
            } catch (StringIndexOutOfBoundsException e) {
                ui.showErrorMessage("\t☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            break;
        case "event":
            try {
                String taskName = inputString.substring(6, inputString.indexOf("/from") - 1);
                String from = inputString.substring(inputString.indexOf("/from") + 6, inputString.indexOf("/to") - 1);
                String to = inputString.substring(inputString.indexOf("/to") + 4);
                Task newTask = new Event(taskName, from, to);
                tasks.addToTaskList(newTask);
                ui.addTaskOutputText(newTask, tasks.size());
            } catch (StringIndexOutOfBoundsException e) {
                ui.showErrorMessage("\t☹ OOPS!!! The description of an event cannot be empty.");
            }
            break;
        case "delete":
            taskIndex = Integer.parseInt(inputString.split(" ")[1]) - 1;
            ui.printDeleteMessage(tasks.taskToString(taskIndex), taskIndex, tasks.size());
            tasks.deleteTask(taskIndex);
            break;
        default:
            ui.showErrorMessage("\t☹ OOPS!!! I'm sorry, but I don't know what that means. Try again using either mark <index>, unmark <index>, todo <task>, deadline <task /by ..>, event <task /from .. /to ..>, or bye.");
        }
        ui.printDivider();
        try {
            storage.save(tasks);
        } catch (DukeException e) {
            ui.showErrorMessage("\tError saving file.");
        }
    }

    /**
     * Provides the entry point for the program.
     * @param args
     */
    public static void main(String[] args) {
        // get new duke instance
        Duke duke = new Duke("data/tasks.txt");

        // start program
        duke.run();
    }

}
