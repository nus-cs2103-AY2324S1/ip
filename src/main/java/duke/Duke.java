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
     * Constructs a Duke object. Catches DukeException if there is an error loading
     * the file.
     * 
     * @param filePath Path to the file to be loaded.
     */
    public Duke(String filePath) {
        // use assertions
        assert filePath != null : "File path cannot be null";
        assert !filePath.isEmpty() : "File path cannot be empty";
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
     * Default constructor for Duke. Sets the default file path to "data/tasks.txt".
     */
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

            // bye
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        byeCommand();
    }

    private String handleTextInput(String inputString) {
        assert inputString != null : "Input string cannot be null";
        assert !inputString.isEmpty() : "Input string cannot be empty";
        // handle key logic here.
        String command = inputString.split(" ")[0];

        switch (command) {
        case "bye":
            return byeCommand();

        case "list":
            return listCommand();

        case "mark":
            return markCommand(inputString);

        case "unmark":
            return unmarkCommand(inputString);

        case "todo":
            return toDoCommand(inputString);
            
        case "deadline":
            return deadlineCommand(inputString);
            
        case "event":
            return eventCommand(inputString);
            
        case "delete":
            return deleteCommand(inputString);
            
        case "find":
            return findCommand(inputString);

        case "priority":
            return priorityCommand(inputString);

        default:
            return wrongCommand();
        }

    }

    private String wrongCommand() {
        return ui.showErrorMessage(
                "\t☹ OOPS!!! I'm sorry, but I don't know what that means. Try again using either mark <index>,"
                        + "unmark <index>, todo <task>, deadline <task /by ..>, event <task /from .. /to ..>, or bye.");
    }

    private String priorityCommand(String inputString) {
        int taskIndex;
        // the format will be priority <index> <priority>
        // priority is an integer
        // index is an integer
        // priority is between 1 and 5
        // index is between 1 and taskList.size()
        String[] inputArr = inputString.split(" ");
        int priority = Integer.parseInt(inputArr[2]);
        taskIndex = Integer.parseInt(inputArr[1]) - 1;
        tasks.setPriority(taskIndex, priority);
        return ui.printPriorityMessage(tasks.taskToString(taskIndex), priority);
    }

    private String findCommand(String inputString) {
        String keyword = inputString.substring(5);
        String outputString = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.taskToString(i).contains(keyword)) {
                outputString += tasks.taskToString(i) + "\n";
            }
        }
        return ui.printTaskList(outputString);
    }

    private String deleteCommand(String inputString) {
        int taskIndex;
        taskIndex = Integer.parseInt(inputString.split(" ")[1]) - 1;
        String msg = ui.printDeleteMessage(tasks.taskToString(taskIndex), taskIndex, tasks.size());
        tasks.deleteTask(taskIndex);
        return msg;
    }

    private String eventCommand(String inputString) {
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
    }

    private String deadlineCommand(String inputString) {
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
                return ui.showErrorMessage("\tInvalid date format. Please use yyyy-mm-dd.");
            }
        } catch (StringIndexOutOfBoundsException e) {
            return ui.showErrorMessage("\t☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    private String toDoCommand(String inputString) {
        try {
            String taskName = inputString.substring(5);
            Task newTask = new ToDo(taskName);
            tasks.addToTaskList(newTask);
            assert !tasks.isEmpty() : "Task list should not be empty";

            return ui.addTaskOutputText(newTask, tasks.size());
        } catch (StringIndexOutOfBoundsException e) {
            return ui.showErrorMessage("\t☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private String unmarkCommand(String inputString) {
        int taskIndex;
        taskIndex = Integer.parseInt(inputString.split(" ")[1]) - 1;
        tasks.unmarkTaskAsDone(taskIndex);
        return ui.unmarkTaskAsDoneMessage(tasks.taskToString(taskIndex));
    }

    private String markCommand(String inputString) {
        int taskIndex;
        // taskIndex is the second word in the input string
        taskIndex = Integer.parseInt(inputString.split(" ")[1]) - 1;

        tasks.markTaskAsDone(taskIndex);

        return ui.markTaskAsDoneMessage(tasks.taskToString(taskIndex));
    }

    private String listCommand() {
        return ui.printTaskList(tasks.toString());
    }

    private String byeCommand() {
        return ui.sendFarewell();
    }

    // Obtains the response from the input
    // Response is sent to the GUI
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
     * 
     * @param args
     */
    public static void main(String[] args) {
        // get new duke instance
        Duke duke = new Duke("data/tasks.txt");

        // start program
        duke.run();
    }

}
