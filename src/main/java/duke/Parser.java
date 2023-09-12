package duke;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;


/**
 * The duke.Parser class is responsible for interpreting user commands and converting them into actions or tasks.
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
     * Checks if the user input is a find command.
     *
     * @param userInput The user's input.
     * @return True if the input is a find command, false otherwise.
     */
    public static boolean isFindCommand(String userInput) {
        return userInput.startsWith("find");
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
     * @param tasks     The current duke.TaskList with all the tasks.
     * @param ui        The current duke.Ui for user interaction.
     * @return True if the program should continue running, false if it should exit.
     * @throws DukeException If there's an issue with the user input or task operations.
     */
    public static boolean parseCommand(String userInput, TaskList tasks, Ui ui) throws DukeException {
        String[] words = userInput.split(" ");
        if (words.length == 2) {
            if (isMarkCommand(words[0])) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                String output = tasks.markTaskAsDone(taskIndex);
                ui.showMessage(output);
                return true;
            }

            if (isUnmarkCommand(words[0])) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                String output = tasks.unmarkTaskAsDone(taskIndex);
                ui.showMessage(output);
                return true;
            }

            if (isDeleteCommand(words[0])) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                String output = tasks.deleteTask(taskIndex);
                ui.showMessage(output);
                return true;
            }
        }

        if (isListCommand(userInput)) {
            ui.showList(tasks);
        } else if (isExitCommand(userInput)) {
            ui.showBye();
            return false;
        } else if (isFindCommand(userInput)) {
            String keyword = userInput.replaceFirst("find", "").trim();
            if (keyword.isEmpty()) {
                throw new InvalidInputException("☹ OOPS!!! I'm sorry, please provide a keyword to search.");
            }
            ui.showMessage(tasks.findTasks(keyword));
        } else if (isValidCommand(userInput)) {
            ui.showMessage(tasks.addTask(userInput));
        } else {
            throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }

    /**
     * Parses the user's input and performs the corresponding operations.
     *
     * @param userInput The user's input.
     * @param tasks     The current duke.TaskList with all the tasks.
     * @param ui        The current duke.Ui for user interaction.
     * @return String the String response of the GUI chat box.
     * @throws DukeException If there's an issue with the user input or task operations.
     */
    public static String parseInput(String userInput, TaskList tasks, Ui ui) throws DukeException {
        String[] words = userInput.split(" ");
        if (words.length == 2) {
            if (isMarkCommand(words[0])) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                return tasks.markTaskAsDone(taskIndex);
            }

            if (isUnmarkCommand(words[0])) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                return tasks.unmarkTaskAsDone(taskIndex);
            }

            if (isDeleteCommand(words[0])) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                return tasks.deleteTask(taskIndex);
            }
        }

        if (isListCommand(userInput)) {
            return ui.showList(tasks);
        } else if (isExitCommand(userInput)) {
            return ui.showBye();
        } else if (isFindCommand(userInput)) {
            String keyword = userInput.replaceFirst("find", "").trim();
            if (keyword.isEmpty()) {
                throw new InvalidInputException("☹ OOPS!!! I'm sorry, please provide a keyword to search.");
            }
            assert !keyword.isEmpty() : "Keyword should not be empty";
            return tasks.findTasks(keyword);
        } else if (isValidCommand(userInput)) {
            return tasks.addTask(userInput);
        } else {
            throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
