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

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String showIntroduction() {
        return ui.showIntroduction();
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

    private String handleTextInput(String inputString) {
        // handle key logic here.
        String command = inputString.split(" ")[0];
        int taskIndex;

        switch (command) {
        case "bye":
            return ui.sendFarewell();
        case "list":
            return ui.printTaskList(tasks.toString());
        case "mark":
            taskIndex = Integer.parseInt(inputString.split(" ")[1]) - 1;
            // tasks.get(taskIndex).markAsDone();
            tasks.markTaskAsDone(taskIndex);
            return ui.markTaskAsDoneMessage(tasks.taskToString(taskIndex));
        case "unmark":
            taskIndex = Integer.parseInt(inputString.split(" ")[1]) - 1;
            tasks.unmarkTaskAsDone(taskIndex);
            return ui.unmarkTaskAsDoneMessage(tasks.taskToString(taskIndex));
        case "todo":
            try {
                String taskName = inputString.substring(5);
                Task newTask = new ToDo(taskName);
                tasks.addToTaskList(newTask);
                return ui.addTaskOutputText(newTask, tasks.size());
            } catch (StringIndexOutOfBoundsException e) {
                return ui.showErrorMessage("\t☹ OOPS!!! The description of a todo cannot be empty.");
            }
        case "deadline":
            try {
                // stop before /by
                String taskName = inputString.substring(9, inputString.indexOf("/by") - 1);
                // get day
                String deadline = inputString.substring(inputString.indexOf("/by") + 4);
                try {

                    Task newTask = new Deadline(taskName, deadline);
                    tasks.addToTaskList(newTask);
                    return ui.addTaskOutputText(newTask, tasks.size());
                } catch (java.time.format.DateTimeParseException e) {
                    // System.out.println("Error: " + e.getMessage());
                    return ui.showErrorMessage("\tInvalid date format. Please use yyyy-mm-dd.");
                }
            } catch (StringIndexOutOfBoundsException e) {
                return ui.showErrorMessage("\t☹ OOPS!!! The description of a deadline cannot be empty.");
            }
        case "event":
            try {
                String taskName = inputString.substring(6, inputString.indexOf("/from") - 1);
                String from = inputString.substring(inputString.indexOf("/from") + 6, inputString.indexOf("/to") - 1);
                String to = inputString.substring(inputString.indexOf("/to") + 4);
                Task newTask = new Event(taskName, from, to);
                tasks.addToTaskList(newTask);
                return ui.addTaskOutputText(newTask, tasks.size());
            } catch (StringIndexOutOfBoundsException e) {
                return ui.showErrorMessage("\t☹ OOPS!!! The description of an event cannot be empty.");
            }
        case "delete":
            taskIndex = Integer.parseInt(inputString.split(" ")[1]) - 1;
            String msg = ui.printDeleteMessage(tasks.taskToString(taskIndex), taskIndex, tasks.size());
            tasks.deleteTask(taskIndex);
            return msg;
        case "find":
            String keyword = inputString.substring(5);
            String outputString = "";
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.taskToString(i).contains(keyword)) {
                    outputString += tasks.taskToString(i) + "\n";
                }
            }
            return ui.printTaskList(outputString);
        default:
            return ui.showErrorMessage(
                    "\t☹ OOPS!!! I'm sorry, but I don't know what that means. Try again using either mark <index>, unmark <index>, todo <task>, deadline <task /by ..>, event <task /from .. /to ..>, or bye.");
                }

    }

    // this handles the response to the input
    // the response is then sent to the GUI
    public String getResponse(String input) {
        String resp = handleTextInput(input);
        try {
            storage.save(tasks);
        } catch (DukeException e) {
            return ui.showErrorMessage("\tError saving file." + " " + e.getMessage());
        }
        return resp;
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
