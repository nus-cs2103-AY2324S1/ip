package chadbod;

/**
 * Represents a parser that is responsible for parsing user input
 * and generating ParsedCommand objects.
 */
public class Parser {

    /**
     * Parse the user input into a ParsedCommand object.
     *
     * @param userInput The user input to be parsed.
     * @return A ParsedCommand object containing the parsed command and details.
     * @throws InvalidInputException If the input does not match any valid command.
     */
    public static ParsedCommand parseCommand(String userInput) throws InvalidInputException {
        Command command = null;
        String[] commandSegments = userInput.split(" ", 2);
        if (commandSegments.length > 0) {
            String commandString = commandSegments[0];
            for (Command cmd : Command.values()) {
                if (cmd.getValue().equals(commandString)) {
                    command = cmd;
                    break;
                }
            }
        }
        if (command == null) {
            throw new InvalidInputException();
        }
        String details = "";
        if (commandSegments.length > 1) {
            details = commandSegments[1];
        }
        return new ParsedCommand(command, details);
    }
}
