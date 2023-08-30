package duke;

public class Parser {

    /**
     * Handles user input and performs the relevant actions based on the commands.
     * Continuously listens for user input until the "bye" command is entered.
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
            } else {
                throw new InvalidInputExpression("Invalid input!! Specify commands as list, mark, unmark, or deadline, event and todo followed by the task please la dei!\n");
            }
        } catch (InvalidInputExpression e) {
            System.out.println(e.getMessage());
        }

        return new String[]{""};
    }
}
