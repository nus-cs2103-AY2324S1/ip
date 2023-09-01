/**
 * A class that makes sense of user inputs.
 */
public class Parser {
    /**
     * Parses user input and carries out any follow-up action (e.g. Task creation).
     * Solution below inspired by addressbook Level 2 implementation.
     * @param input
     */
    public static Command parse(String input) throws ChatbotException {
        // Obtains first word of user input, which should state the action to be performed
        String action = input.split(" ")[0].toLowerCase();

        switch (action) {
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case MarkCommand.COMMAND_WORD:
                return new MarkCommand(input);
            case UnmarkCommand.COMMAND_WORD:
                return new UnmarkCommand(input);
            case TodoCommand.COMMAND_WORD:
                return new TodoCommand(input);
            case DeadlineCommand.COMMAND_WORD:
                return new DeadlineCommand(input);
            case EventCommand.COMMAND_WORD:
                return new EventCommand(input);
            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(input);
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            default:
                throw new ChatbotException("No such command lah.");
        }
    }
}
