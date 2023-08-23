public class CommandParser {

    public CommandParser() {}

    public Command parse(String cmd) {
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

        if (commandType.equals("bye")) {
            return new Command(CommandType.BYE, new String[]{});
        }

        if (commandType.equals("todo")) {
            return new Command(CommandType.TODO, new String[]{argumentString});
        }

        if (commandType.equals("deadline")) {
            String[] deadlineParts = argumentString.split("/by", 2);
            return new Command(CommandType.DEADLINE, new String[]{deadlineParts[0].trim(), deadlineParts[1].trim()});
        }

        if (commandType.equals("event")) {
            String[] eventParts = argumentString.split("/from|/to", 3);
            return new Command(CommandType.EVENT, new String[]{eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim()});
        }

        return new Command(CommandType.TODO, new String[]{});
    }
}
