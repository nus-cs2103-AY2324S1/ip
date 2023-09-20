package duke;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import javafx.application.Platform;

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
     * Checks if the user's input is "delete", "unmark" or "mark" command.
     *
     * @param words the user's input in a String array
     * @return True if the input is "delete", "unmark" or "mark" command.
     */
    private static boolean isTaskCommand(String[] words) {
        boolean isCorrectType = (isMarkCommand(words[0]) || isUnmarkCommand(words[0]) || isDeleteCommand(words[0]));
        boolean isCorrectLength = words.length == 2;
        return isCorrectLength && isCorrectType;
    }

    /**
     * Convert the task index from user input to an Integer.
     * This is used for 'delete', 'unmakr' and 'mark' commands.
     *
     * @param input the task index in user's input.
     * @return the task index as an Integer.
     * @throws DukeException if there is an issue parsing the task index into a Integer.
     */
    private static Integer convertToInt(String input) throws DukeException {
        try {
            Integer index = Integer.parseInt(input);
            return index;
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!! Invalid task index. Task index should be a number!");
        }
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
        String[] wordsCli = userInput.split(" ");
        if (isTaskCommand(wordsCli)) {
            Integer taskIndex = convertToInt(wordsCli[1]) - 1;
            String output;
            if (isMarkCommand(wordsCli[0])) {
                output = tasks.markTaskAsDone(taskIndex);
            } else if (isUnmarkCommand(wordsCli[0])) {
                output = tasks.unmarkTaskAsDone(taskIndex);
            } else {
                output = tasks.deleteTask(taskIndex);
            }
            ui.showMessage(output);
            return true;
        }

        if (isListCommand(userInput)) {
            ui.showList(tasks);
        } else if (isExitCommand(userInput)) {
            ui.showBye();
            return false;
        } else if (isFindCommand(userInput)) {
            String keyword = userInput.replaceFirst("find", "").trim();
            if (keyword.isEmpty()) {
                throw new DukeException("☹ OOPS!!! I'm sorry, please provide a keyword to search.");
            }
            ui.showMessage(tasks.findTasks(keyword));
        } else if (isValidCommand(userInput)) {
            ui.showMessage(tasks.addTask(userInput));
        } else {
            throw new InvalidInputException();
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
        String[] wordsGui = userInput.split(" ");
        if (isTaskCommand(wordsGui)) {
            Integer taskIndex = convertToInt(wordsGui[1]) - 1;
            if (isMarkCommand(wordsGui[0])) {
                return tasks.markTaskAsDone(taskIndex);
            } else if (isUnmarkCommand(wordsGui[0])) {
                return tasks.unmarkTaskAsDone(taskIndex);
            } else {
                return tasks.deleteTask(taskIndex);
            }
        }

        if (isListCommand(userInput)) {
            return ui.showList(tasks);
        } else if (isExitCommand(userInput)) {
            Platform.exit();
            return ui.showBye();
        } else if (isFindCommand(userInput)) {
            String keyword = userInput.replaceFirst("find", "").trim();
            if (keyword.isEmpty()) {
                throw new DukeException("☹ OOPS!!! I'm sorry, please provide a keyword to search.");
            }
            assert !keyword.isEmpty() : "Keyword should not be empty";
            return tasks.findTasks(keyword);
        } else if (isValidCommand(userInput)) {
            return tasks.addTask(userInput);
        } else {
            throw new InvalidInputException();
        }
    }
}
