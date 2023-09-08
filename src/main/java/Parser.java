/**
 * Parses user commands to create corresponding Command objects.
 */
public class Parser {

    /**
     * Parses the user input command and returns the corresponding Command object.
     *
     * @param fullCommand The user's input command.
     * @return A Command object representing the parsed command.
     * @throws DukeException If there is an error in the command or the input is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.toLowerCase().startsWith("list")) {
            return new ListCommand();
        } else if (fullCommand.toLowerCase().startsWith("todo")) {
            // Check if the input contains a valid description
            if (fullCommand.length() <= 5) {
                throw new DukeException("The description of a ToDo task cannot be empty.");
            }
            return new AddTodo(fullCommand);
        } else if (fullCommand.toLowerCase().startsWith("deadline")) {
            // Check if the input contains a valid description
            if (fullCommand.length() <= 8 || !fullCommand.contains("/by")) {
                throw new DukeException("Incorrect format for deadline. Use: 'deadline [task] /by [YYYY-MM-DD HH:mm]'");
            }
            return new AddDeadline(fullCommand);
        } else if (fullCommand.toLowerCase().startsWith("event")) {
            // Check if the input contains a valid description and both /from and /to
            if (fullCommand.length() <= 5 || !fullCommand.contains("/from") || !fullCommand.contains("/to")) {
                throw new DukeException("Incorrect format for event. Use: 'event [task] /from [YYYY-MM-DD HH:mm] /to [YYYY-MM-DD HH:mm]'");
            }
            return new AddEvent(fullCommand);
        } else if (fullCommand.toLowerCase().startsWith("mark")) {
            if (!fullCommand.matches("^mark\\s\\d+$")) {
                throw new DukeException("Invalid index. Please provide the command followed by a valid integer index.");
            }
            int taskIndex = Integer.parseInt(fullCommand.substring(5).trim());
            return new EditCommand("mark", taskIndex);
        } else if (fullCommand.isEmpty()) {
            throw new DukeException("Empty command is not recognized. Use 'help' for more information.");
        } else if (fullCommand.toLowerCase().startsWith("unmark")) {
            if (!fullCommand.matches("^unmark\\s\\d+$")) {
                throw new DukeException("Invalid index. Please provide the command followed by a valid integer index.");
            }
            int taskIndex = Integer.parseInt(fullCommand.substring(7).trim());
            return new EditCommand("unmark", taskIndex);
        } else if (fullCommand.toLowerCase().startsWith("delete")) {
            if (!fullCommand.matches("^delete\\s\\d+$")) {
                throw new DukeException("Invalid index. Please provide the command followed by the index.");
            }
            int i = Integer.parseInt(fullCommand.substring(7).trim());
            return new DeleteCommand(i);
        } else if (fullCommand.toLowerCase().startsWith("help")) {
            return new HelpCommand();
        } else {
            throw new DukeException("Please specify a valid command. For more info, use: help");
        }
    }
}
