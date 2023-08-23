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

        return new Command(CommandType.TASK, new String[]{argumentString});
    }
}
