package chad.chatengine;

/**
 * Utility class for parsing chat commands and input.
 */
public class Parser {

    /**
     * Enumeration of possible command types.
     */
    public enum CommandType {
        BYE, MARK, UNMARK, LIST, TODO, DEADLINE, EVENT, DELETE, FIND, UNKNOWN
    }

    /**
     * Parses the type of command.
     * 
     * @param command the command string.
     * @return the type of command.
     */
    public static CommandType parseCommandType(String command) {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.UNKNOWN;
        }
    }

    /**
     * Parses the input string into command and arguments.
     * 
     * @param input the user's input string.
     * @return a String array containing the command and its arguments.
     * @throws ChadException if the input is invalid or incomplete.
     */
    public static String[] parseInput(String input) throws ChadException {
        if (input.trim().isEmpty()) {
            throw new ChadException.InvalidArgumentException("Input cannot be empty.");
        }

        String[] parts = input.split(" ", 2);
        CommandType command = parseCommandType(parts[0]);

        switch (command) {
        case MARK:
        case UNMARK:
        case DELETE:
            if (parts.length < 2) {
                throw new ChadException.InvalidArgumentException("Missing index for " + command);
            }
            break;
        case TODO:
            if (parts.length < 2) {
                throw new ChadException.InvalidArgumentException("Missing task description for ToDo.");
            }
            break;
        case DEADLINE:
            if (parts.length < 2 || !parts[1].contains(" /by ")) {
                throw new ChadException.InvalidArgumentException(
                        "Invalid format for Deadline. Use: deadline {task} /by {date}");
            }
            break;
        case EVENT:
            if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
                throw new ChadException.InvalidArgumentException(
                        "Invalid format for Event. Use: event {task} /from {start} /to {end}");
            }
            break;
        case LIST:
        case BYE:
            break;
        case FIND:
            if (parts.length < 2) {
                throw new ChadException.InvalidArgumentException("Missing keyword for find.");
            }
            break;
        case UNKNOWN:
        default:
            throw new ChadException.InvalidCommandException("Unknown command: " + parts[0]);
        }

        return parts;
    }
}
