package sally;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The user's input command.
     * @return The Command object based on the input.
     * @throws SallyException If the input command is invalid.
     */
    public static Command parse(String input) throws SallyException {
        // if no space then exception handling
        if (input.startsWith("list")) {
            return new ListCommand();
        } else if (!input.contains(" ")) {
            throw new SallyException("Sorry, I do not understand the command :-(");
        } else {
            String[] parts = input.split(" ", 2);
            String commandType = parts[0].toLowerCase();

            if (commandType.equals("mark")) {
                return new MarkCommand(parts[1]);
            } else if (commandType.equals("unmark")) {
                return new UnmarkCommand(parts[1]);
            } else if (commandType.equals("delete")) {
                return new DeleteCommand(parts[1]);
            } else if (commandType.equals("todo")) {
                return new AddTodoCommand(parts[1]);
            } else if (commandType.equals("deadline")) {
                return new AddDeadlineCommand(parts[1]);
            } else if (commandType.equals("event")) {
                return new AddEventCommand(parts[1]);
            } else if (commandType.equals("find")) {
                return new FindCommand(parts[1]);
            } else {
                throw new SallyException("Sorry, I do not understand the command :-(");
            }
        }
    }
}
