package duke;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        String[] split = fullCommand.split(" ");
        if (fullCommand.isEmpty() || split.length == 0) {
            throw new DukeException("OOPS!!! You have not entered anything!");
        }

        Keyword key;
        try {
            key = Keyword.valueOf(split[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means.");
        }

        if (split.length == 1) {
            return parseOneWordCommand(key);
        }
        String commandBody = fullCommand.substring(split[0].length() + 1);
        return parseMultiWordCommand(key, commandBody);
    }

    private static Command parseOneWordCommand(Keyword key) throws DukeException {
        String err = String.format("OOPS!!! The description of a %s cannot be empty.", key.getKeyword());
        switch (key){
        case BYE:
            return new ExitCommand();

        case LIST:
            return new ListCommand();

        case TODO:
            throw new TodoException(err);

        case DEADLINE:
            throw new DeadlineException(err);

        case EVENT:
            throw new EventException(err);

        case MARK:
        case UNMARK:
        case DELETE:
            throw new ManipulateException(err, key.getKeyword());

        case PRINT_DATE:
            throw new PrintDateException(err);
        }
        // Here should not be reached, as all the cases for key is considered.
        return null;
    }

    private static Command parseMultiWordCommand(Keyword key, String commandBody) throws DukeException {
        switch (key) {
        case BYE:
            if (commandBody.equals(Ui.NAME)) {
                return new ExitCommand();
            }
            // fall through
        case LIST:
            String errMessage = Ui.connectTwoLine(
                    String.format("OOPS!!! The command for %s is invalid.", key.getKeyword()),
                    String.format("Enter in the form: \"%s\"", key.getKeyword()));
            throw new DukeException(errMessage);

        case MARK:
        case UNMARK:
            return new MarkCommand(key, commandBody);

        case DELETE:
            return new DeleteCommand(commandBody);

        case TODO:
        case DEADLINE:
        case EVENT:
            return new AddCommand(key, commandBody);

        case PRINT_DATE:
            return new PrintDateCommand(commandBody);
        }
        // Here should not be reached, as all the cases for key is considered.
        return null;
    }
}
