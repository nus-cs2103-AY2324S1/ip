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
        try {
            if (userInput.startsWith("mark")) {
                return new String[]{"mark", userInput};
            } else if (userInput.startsWith("unmark")) {
                return new String[]{"unmark", userInput};
            } else if (userInput.startsWith("delete")) {
                return new String[]{"delete", userInput};
            } else if (userInput.startsWith("list")) {
                return new String[]{"list", userInput};
            } else if (userInput.startsWith("todo")) {
                return new String[]{"todo", userInput};
            } else if (userInput.startsWith("deadline")) {
                return new String[]{"deadline", userInput};
            } else if (userInput.startsWith("event")) {
                return new String[]{"event", userInput};
            } else if (userInput.startsWith("find")) {
                return new String[]{"find", userInput};
            } else {
                throw new InvalidInputExpression("Invalid input!! "
                        + "Specify commands as list, mark, unmark, or deadline, event "
                        + "and todo followed by the task please la dei!\n");
            }
        } catch (InvalidInputExpression e) {
            System.out.println(e.getMessage());
        }

        return new String[]{""};
    }
}
