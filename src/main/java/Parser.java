public class Parser {
    /**
     * Parses the user input into a Command object.
     *
     * @param fullCommand The full input string from the user.
     * @return The corresponding Command object.
     */
    public static Command parse(String userInput)  {
        switch (userInput.toLowerCase()) {
            case "bye":
                return new ExitCommand();
            default:
            {
                System.out.println(userInput);
                return new EchoCommand();
            }

        }
    }
}
