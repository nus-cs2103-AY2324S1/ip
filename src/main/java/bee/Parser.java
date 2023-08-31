package bee;

/**
 * Handles the parsing of user commands and delegates the corresponding actions.
 * Provides methods for parsing user input and executing actions based on the input.
 */
public class Parser {

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

    /**
     * Parses the user's command, executes the corresponding action, and handles exceptions.
     *
     * @param userInput The user's input command.
     * @param tasks     The TaskList instance to manage tasks.
     * @param storage   The Storage instance to manage data storage.
     * @param ui        The Ui instance to handle user interface.
     * @return True if the chatbot should continue running, false if it should exit.
     */
    public static boolean parseUserCommand(String userInput, TaskList tasks, Storage storage, Ui ui) {
        String[] splitInput = userInput.split(" ");
        String command = splitInput[0].toLowerCase();
        try {
            switch (command) {
                case "bye":
                    ui.farewell();
                    return false;
                case "list":
                    tasks.listAllTasks();
                    break;
                case "todo":
                    tasks.createTask(TaskClass.TODO, userInput);
                    break;
                case "deadline":
                    tasks.createTask(TaskClass.DEADLINE, userInput);
                    break;
                case "event":
                    tasks.createTask(TaskClass.EVENT, userInput);
                    break;
                case "mark":
                    tasks.updateTask(TaskAction.MARK, userInput);
                    break;
                case "unmark":
                    tasks.updateTask(TaskAction.UNMARK, userInput);
                    break;
                case "delete":
                    tasks.updateTask(TaskAction.DELETE, userInput);
                    break;
                default:
                    throw new BeeException("Sorry, you need to use a command!");

            }
        } catch (BeeException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println("    _  _\n" +
                    "   | )/ )\n" +
                    "\\\\ |//,' __\n" +
                    "(\")(_)-\"()))=-\n" +
                    "   (\\\\ BZZZZZZZ!!!! Something went very wrong!!");
        }
        storage.saveTasksToFile();
        return true;
    }
}
