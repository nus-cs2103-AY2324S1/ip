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
        assert userInput != null : "User input cannot be null."; // Assumption: User input should not be null
        assert tasks != null : "TaskList object cannot be null."; // Assumption: TaskList should not be null
        assert storage != null : "Storage object cannot be null."; // Assumption: Storage should not be null
        assert ui != null : "Ui object cannot be null."; // Assumption: Ui should not be null

        String[] splitInput = userInput.split(" ");
        String command = splitInput[0].toLowerCase();
        StringBuilder newUserInput = new StringBuilder();
        for (int i = 1; i < splitInput.length; i++) {
            if (i > 1) {
                newUserInput.append(" ");
            }
            newUserInput.append(splitInput[i]);
        }
        String recombinedUserInput = newUserInput.toString();
        try {
            switch (command) {
            case "bye":
                System.exit(0);
                return ui.farewell();
            case "list":
                return tasks.listAllTasks();
            case "t": // 't' as an alias for 'todo'
            case "todo":
                return tasks.createTask(TaskClass.TODO, recombinedUserInput);
            case "d": // 'd' as an alias for 'deadline'
            case "deadline":
                return tasks.createTask(TaskClass.DEADLINE, recombinedUserInput);
            case "e": // 'e' as an alias for 'event'
            case "event":
                return tasks.createTask(TaskClass.EVENT, recombinedUserInput);
            case "m": // 'm' as an alias for 'mark'
            case "mark":
                return tasks.updateTask(TaskAction.MARK, recombinedUserInput);
            case "u": // 'u' as an alias for 'unmark'
            case "unmark":
                return tasks.updateTask(TaskAction.UNMARK, recombinedUserInput);
            case "del": // 'del' as an alias for 'delete'
            case "delete":
                return tasks.updateTask(TaskAction.DELETE, recombinedUserInput);
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
