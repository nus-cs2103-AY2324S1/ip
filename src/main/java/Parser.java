/**
 * The Parser class is responsible for interpreting user commands and converting them into actions or tasks.
 */
public class Parser {
    /**
     * Checks if the user input is an exit command.
     *
     * @param userInput The user's input.
     * @return True if the input is an exit command, false otherwise.
     */
    public static boolean isExitCommand(String userInput) {
        return userInput.equals("bye");
    }

    /**
     * Checks if the user input is a list command.
     *
     * @param userInput The user's input.
     * @return True if the input is a list command, false otherwise.
     */
    public static boolean isListCommand(String userInput) {
        return userInput.equals("list");
    }

    /**
     * Checks if the user input is a mark command.
     *
     * @param userInput The user's input.
     * @return True if the input is a mark command, false otherwise.
     */
    public static boolean isMarkCommand(String userInput) {
        return userInput.startsWith("mark");
    }

    /**
     * Checks if the user input is an unmark command.
     *
     * @param userInput The user's input.
     * @return True if the input is an unmark command, false otherwise.
     */
    public static boolean isUnmarkCommand(String userInput) {
        return userInput.startsWith("unmark");
    }

    /**
     * Checks if the user input is a delete command.
     *
     * @param userInput The user's input.
     * @return True if the input is a delete command, false otherwise.
     */
    public static boolean isDeleteCommand(String userInput) {
        return userInput.startsWith("delete");
    }

    /**
     * Checks if the user input is a valid task type.
     *
     * @param userInput The user's input.
     * @return True if the input is a valid task type, false otherwise.
     */
    public static boolean isValidCommand(String userInput) {
        return userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event");
    }

    /**
     * Parses the user's input and performs the corresponding operations.
     *
     * @param userInput The user's input.
     * @param tasks     The current TaskList with all the tasks.
     * @param ui        The current Ui for user interaction.
     * @return True if the program should continue running, false if it should exit.
     * @throws DukeException If there's an issue with the user input or task operations.
     */
    public static boolean parseCommand(String userInput, TaskList tasks, Ui ui) throws DukeException {
        String[] words = userInput.split(" ");
        if (words.length == 2) {
            if (isMarkCommand(words[0])) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                ui.showMessage(tasks.markTaskAsDone(taskIndex));
                return true;
            }

            if (isUnmarkCommand(words[0])) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                ui.showMessage(tasks.unmarkTaskAsDone(taskIndex));
                return true;
            }

            if (isDeleteCommand(words[0])) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                ui.showMessage(tasks.deleteTask(taskIndex));
                return true;
            }
        }

        if (isListCommand(userInput)) {
            ui.showList(tasks);
        } else if (isExitCommand(userInput)) {
            ui.showBye();
            return false;
        } else if (isValidCommand(userInput)) {
            ui.showMessage(tasks.addTask(userInput));
        } else {
            throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }
}
