public enum Command {
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    BYE,
    DELETE,
    INVALID,
    DUE;

    public static Command parseCommand(String command) throws InvalidCommandException, WrongUseOfCommandException,
            MissingTaskException, MissingIndexException {
        command = command.trim();

        if (command.startsWith("bye")) {
            if (command.equals("bye")) {
                return Command.BYE;
            }
            throw new WrongUseOfCommandException();
        }

        if (command.startsWith("list")) {
            if (command.equals("list")) {
                return Command.LIST;
            }
            throw new WrongUseOfCommandException();
        }

        if (command.startsWith("mark") || command.startsWith("unmark") ||
                command.startsWith("delete") || command.startsWith("due")) {
            try {
                String res = command.split(" ", 2)[0];

                return Command.valueOf(res.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new MissingIndexException(command);
            }
        }

        if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
            if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                throw new MissingTaskException(command);
            }

            return Command.valueOf(command.split(" ", 2)[0].toUpperCase());
        }

        throw new InvalidCommandException(command);
    }
}
