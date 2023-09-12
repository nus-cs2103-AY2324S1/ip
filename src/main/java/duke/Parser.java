package duke;

/**
 * Class to parse the input
 */
public class Parser {

    /**
     * Handles user input and determines the relevant command and input for further processing.
     * Parses the user input and extracts the command type and associated input.
     * Supports commands such as "mark," "unmark," "delete," "list," "todo," "deadline," and "event."
     *
     * @param userInput The input string entered by the user.
     * @return An array containing the command type and associated input.
     * @throws InvalidInputExpression If the user input does not match any supported command.
     */
    public String[] handleUserInput(String userInput) throws InvalidInputExpression {
        String[] parts = userInput.trim().split(" ", 2);

        if (parts.length < 2) {
            throw new InvalidInputExpression("Invalid input!! Specify commands as list, mark, unmark, deadline, event, or todo, followed by the task please la dei!\n");
        }

        String command = parts[0];

        switch (command) {
        case "mark":
            //Fallthrough
        case "unmark":
            //Fallthrough
        case "delete":
            //Fallthrough
        case "list":
            //Fallthrough
        case "todo":
            //Fallthrough
        case "deadline":
            //Fallthrough
        case "event":
            //Fallthrough
        case "find":
            return new String[]{command, userInput};
        default:
            throw new InvalidInputExpression("Invalid input!! Specify commands as list, mark, unmark, deadline, event, or todo, followed by the task please la dei!\n");
        }
    }
}
