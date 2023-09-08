package bot.utils;

import java.util.regex.Pattern;

import bot.exceptions.InvalidArgumentException;
import bot.exceptions.InvalidCommandException;
import bot.utils.commands.Command;
import bot.utils.tasks.Task;

/**
 * Abstraction for making sense of user commands.
 */
public class Parser {
    /**
     * Regex pattern for delete commands.
     */
    private static final Pattern PATTERN_DELETE = Pattern.compile("delete -?\\d+");
    /**
     * Regex pattern for mark commands.
     */
    private static final Pattern PATTERN_MARK = Pattern.compile("mark -?\\d+");
    /**
     * Regex pattern for unmark commands.
     */
    private static final Pattern PATTERN_UNMARK = Pattern.compile("unmark -?\\d+");

    /**
     * Default constructor. It does nothing as the parser is not meant to
     * be instantiated.
     */
    public Parser() {}

    /**
     * Parses the command string into a command to be executed.
     * Does not guarantee the command is syntactically correct.
     *
     * @param str Full command string.
     * @return Command object.
     * @throws InvalidCommandException If the command is invalid.
     * @throws InvalidArgumentException If the arguments are clearly invalid.
     */
    public static Command parse(String str) throws InvalidCommandException, InvalidArgumentException {
        String commandWord = str.split(" ", 2)[0].toLowerCase().trim();
        switch (commandWord) {
        case "bye":
            return Command.exit();
        case "list":
            return Command.list();
        case "mark":
            return Command.mark(getMarkIndex(str));
        case "unmark":
            return Command.unmark(getUnmarkIndex(str));
        case "delete":
            return Command.delete(getDeleteIndex(str));
        case "find":
            return Command.find(getFindTerm(str));
        default:
            if (!Task.isTaskCommand(commandWord)) {
                throw new InvalidCommandException();
            }
            return Command.add(str);
        }
    }

    /**
     * Gets the index from the unmark command string.
     *
     * @param str Full command string.
     * @return Unmark index.
     */
    private static int getUnmarkIndex(String str) throws InvalidArgumentException {
        assert str != null;
        if (!isValidUnmarkCommand(str)) {
            throw new InvalidArgumentException("Index to mark must be an integer.");
        }
        return Integer.parseInt(str.substring(7));
    }

    /**
     * Checks if the command string has valid MarkCommand syntax. It ensures the string follows this pattern:
     * "unmark (index)" where index is an integer.
     *
     * @param str Full command string.
     * @return Returns true if it is valid, else false.
     */
    private static boolean isValidUnmarkCommand(String str) {
        assert str != null;
        return PATTERN_UNMARK.matcher(str).matches();
    }

    /**
     * Checks if the command string has valid MarkCommand syntax. It ensures the string follows this pattern:
     * "mark (index)" where index is an integer.
     *
     * @param str Full command string.
     * @return Returns true if it is valid, else false.
     */
    private static boolean isValidMarkCommand(String str) {
        assert str != null;
        return PATTERN_MARK.matcher(str).matches();
    }

    /**
     * Gets the index from the mark command string.
     *
     * @param str Full command string.
     * @return Mark index.
     */
    private static int getMarkIndex(String str) throws InvalidArgumentException {
        assert str != null;
        if (!isValidMarkCommand(str)) {
            throw new InvalidArgumentException("Index to mark must be an integer.");
        }
        return Integer.parseInt(str.substring(5));
    }

    /**
     * Checks if the string has valid FindCommand syntax. It ensures the command follows this pattern:
     * "find (search term)"
     * Note that whitespaces are valid search terms as well, so " book" will find "read book".
     *
     * @param str Raw command string.
     * @return True if it is valid, else false.
     */
    private static boolean isValidFindCommand(String str) {
        assert str != null;
        if (str.trim().equalsIgnoreCase("find")) {
            return false;
        }
        String[] tokens = str.split(" ", 2);
        assert tokens.length > 1;
        String searchTerm = tokens[1].trim();
        return !searchTerm.equals("");
    }

    /**
     * Parses the search term from the full command string.
     *
     * @param str Full command string.
     * @return Search term.
     * @throws InvalidArgumentException If the search term is empty.
     */
    private static String getFindTerm(String str) throws InvalidArgumentException {
        if (!isValidFindCommand(str)) {
            assert str.trim().equals("find");
            throw new InvalidArgumentException("Search term cannot be empty.");
        }
        return str.substring(5).trim();
    }

    /**
     * Checks if the string has valid DeleteCommand syntax. It ensures the command follows this pattern:
     * "delete (index)" where index is an integer.
     *
     * @param str Raw command string.
     * @return True if it is valid, else false.
     */
    private static boolean isValidDeleteCommand(String str) {
        assert str != null;
        return PATTERN_DELETE.matcher(str).matches();
    }

    /**
     * Gets the index from the delete command string.
     *
     * @param str Full command string.
     * @return Delete index.
     */
    private static int getDeleteIndex(String str) throws InvalidArgumentException {
        assert str != null;
        if (!isValidDeleteCommand(str)) {
            throw new InvalidArgumentException("Index to delete must be an integer.");
        }
        return Integer.parseInt(str.substring(7));
    }
}
