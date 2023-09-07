package bee;

import bee.ui.Ui;

/**
 * Handles the parsing of user commands and delegates the corresponding actions.
 * Provides methods for parsing user input and executing actions based on the
 * input.
 */
public class Parser {

    /**
     * Parses the user's command, executes the corresponding action, and handles
     * exceptions.
     *
     * @param userInput The user's input command.
     * @param tasks     The TaskList instance to manage tasks.
     * @param storage   The Storage instance to manage data storage.
     * @param ui        The Ui instance to handle user interface.
     * @return True if the chatbot should continue running, false if it should exit.
     */
    public static String parseUserCommand(String userInput, TaskList tasks, Storage storage, Ui ui) {
        String[] splitInput = userInput.split(" ");
        String command = splitInput[0].toLowerCase();
        try {
            switch (command) {
            case "bye":
                return ui.farewell();
            case "list":
                return tasks.listAllTasks();
            case "todo":
                return tasks.createTask(TaskClass.TODO, userInput);
            case "deadline":
                return tasks.createTask(TaskClass.DEADLINE, userInput);
            case "event":
                return tasks.createTask(TaskClass.EVENT, userInput);
            case "mark":
                return tasks.updateTask(TaskAction.MARK, userInput);
            case "unmark":
                return tasks.updateTask(TaskAction.UNMARK, userInput);
            case "delete":
                return tasks.updateTask(TaskAction.DELETE, userInput);
            case "find":
                return tasks.findTasksByKeyword(userInput);
            default:
                return Ui.returnErrorString(new BeeException("Sorry, you need to use a command!"));
            }
        } catch (BeeException e) {
            return Ui.returnErrorString(e);
        }
    }

    /**
     * Enumeration representing the types of tasks.
     */
    enum TaskClass {
        TODO, DEADLINE, EVENT
    }

    /**
     * Enumeration representing the types of task actions.
     */
    enum TaskAction {
        MARK, UNMARK, DELETE
    }
}
