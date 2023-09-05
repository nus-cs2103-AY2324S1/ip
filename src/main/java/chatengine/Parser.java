package chatengine;
public class Parser {

    public enum CommandType {
        BYE, MARK, UNMARK, LIST, TODO, DEADLINE, EVENT, DELETE, UNKNOWN
    }

    public static CommandType parseCommandType(String command) {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.UNKNOWN;
        }
    }

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
                    throw new ChadException.InvalidArgumentException("Invalid format for Deadline. Use: deadline {task} /by {date}");
                }
                break;
            case EVENT:
                if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
                    throw new ChadException.InvalidArgumentException("Invalid format for Event. Use: event {task} /from {start} /to {end}");
                }
                break;
            case LIST:
            case BYE:
                break;
            case UNKNOWN:
            default:
                throw new ChadException.InvalidCommandException("Unknown command: " + parts[0]);
        }

        return parts;
    }
}
