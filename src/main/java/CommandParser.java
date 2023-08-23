public class CommandParser {

    public CommandParser() {}

    public Command parse(String cmd) throws InvalidCommandException, InvalidTaskFormatException {
        String[] parts = cmd.split(" ", 2);
        String commandType = parts[0].toLowerCase();
        String argumentString = parts.length > 1 ? parts[1] : "";

        if (commandType.equals("list")) {
            return new Command(CommandType.LIST, new String[]{});
        }

        if (commandType.equals("mark") || commandType.equals("unmark")) {
            if (commandType.equals("mark")) {
                return new Command(CommandType.MARK, new String[]{argumentString});
            } else {
                return new Command(CommandType.UNMARK, new String[]{argumentString});
            }
        }

        if (commandType.equals("delete")) {
            return new Command(CommandType.DELETE, new String[]{argumentString});
        }

        if (commandType.equals("bye")) {
            return new Command(CommandType.BYE, new String[]{});
        }

        if (commandType.equals("todo")) {
            argumentString = argumentString.trim();
            if (argumentString.isEmpty()) {
                throw new InvalidTaskFormatException("description", "todo");
            }
            return new Command(CommandType.TODO, new String[]{argumentString});
        }

        if (commandType.equals("deadline")) {
            String[] deadlineParts = argumentString.split("/by", 2);
            for (int i = 0; i < deadlineParts.length; i++) {
                deadlineParts[i] = deadlineParts[i].trim();
                if (deadlineParts[i].isEmpty()) {
                    throw new InvalidTaskFormatException("description or /by", "deadline");
                }
            }
            if (deadlineParts.length < 2) {
                throw new InvalidTaskFormatException("description or /by", "deadline");
            }
            return new Command(CommandType.DEADLINE, new String[]{deadlineParts[0], deadlineParts[1]});
        }

        if (commandType.equals("event")) {
            String[] eventParts = argumentString.split("/from|/to", 3);
            for (int i = 0; i < eventParts.length; i++) {
                eventParts[i] = eventParts[i].trim();
                if (eventParts[i].isEmpty()) {
                    throw new InvalidTaskFormatException("description or /from or /to", "event");
                }
            }
            if (eventParts.length < 3) {
                throw new InvalidTaskFormatException("description or /from or /to", "event");
            }
            return new Command(CommandType.EVENT, new String[]{eventParts[0], eventParts[1], eventParts[2]});
        }

        throw new InvalidCommandException();
    }
}
