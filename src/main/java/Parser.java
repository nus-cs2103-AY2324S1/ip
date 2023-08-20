public class Parser {
    /**
     * Parses the user input into a Command object.
     *
     * @param userInput The input string from the user.
     * @return The corresponding Command object.
     */
    public static Command parse(String userInput)  {
        switch (userInput.toLowerCase()) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            default:
                return new AddCommand();
        }
    }
}
