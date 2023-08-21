public enum Command {
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    BYE,
    INVALID;

    public static Command parseCommand(String command) {
        if (command.equals("bye")) {
            return Command.BYE;
        }

        if (command.equals("list")) {
            return Command.LIST;
        }

        String[] parts = command.split(" ", 2);
        try {
            return Command.valueOf(parts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.INVALID;
        }
    }
}
