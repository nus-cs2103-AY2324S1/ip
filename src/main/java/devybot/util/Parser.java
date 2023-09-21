package devybot.util;

import devybot.exceptions.DevyBotException;
import devybot.exceptions.EmptyDescriptionException;
import devybot.exceptions.NonIntegerInputException;
import devybot.exceptions.UnknownCommandException;

/**
 * The Parser class is responsible for parsing user input and executing
 * corresponding commands.
 */
public class Parser {

    /**
     * Enumerates the supported command types.
     */
    private static enum CommandType {
        TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, LIST, BYE, UNKNOWN, FIND, SEARCH
    }

    /**
     * Determines the type of command based on the user input.
     *
     * @param command The user's input.
     * @return The CommandType corresponding to the input or UNKNOWN if not
     *         recognized.
     */
    public static CommandType getCommandType(String command) {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.UNKNOWN;
        }
    }

    /**
     * Parses user input and executes the corresponding command.
     *
     * @param userInput The user's input.
     * @param taskList  The TaskList object to perform operations on.
     * @throws DevyBotException If an error occurs during command execution.
     */
    public static void parseInputs(String userInput, TaskList taskList) throws DevyBotException {
        String[] wordsArray = userInput.split("\\s+");
        CommandType commandType = getCommandType(wordsArray[0]);

        switch (commandType) {
        case TODO:
            taskList.addTodoTask(userInput);
            break;
        case DEADLINE:
            taskList.addDeadlineTask(userInput);
            break;
        case EVENT:
            taskList.addEventTask(userInput);
            break;
        case MARK:
            int markIndex = getIndex(wordsArray);
            taskList.markTaskAsDone(markIndex);
            break;
        case UNMARK:
            int unmarkIndex = getIndex(wordsArray);
            taskList.markTaskAsUndone(unmarkIndex);
            break;
        case DELETE:
            int deleteIndex = getIndex(wordsArray);
            taskList.deleteTask(deleteIndex);
            break;
        case BYE:
            Ui.exit();
            break;
        case LIST:
            taskList.listTasks();
            break;
        case FIND:
            taskList.findTasks(userInput);
            break;
        case SEARCH:
            taskList.searchTasks(userInput);
            break;
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Extracts and returns the task index from the user input.
     *
     * @param wordsArray The array of words from the user input.
     * @return The index of the task to operate on.
     * @throws EmptyDescriptionException If the task index is missing.
     * @throws NonIntegerInputException  If the task index is not a valid integer.
     */
    public static int getIndex(String[] wordsArray) throws EmptyDescriptionException, NonIntegerInputException {
        try {
            if (wordsArray.length <= 1) {
                throw new EmptyDescriptionException(wordsArray[0].toString());
            }
            int index = Integer.parseInt(wordsArray[1]) - 1;
            return index;
        } catch (NumberFormatException e) {
            throw new NonIntegerInputException();
        }
    }

}
