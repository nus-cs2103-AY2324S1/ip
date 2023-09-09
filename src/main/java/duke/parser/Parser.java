package duke.parser;

/**
 * A parser to parse the user input and commands.
 */
public class Parser {

    /**
     * The types of Commands that can be parsed.
     */
    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND,
        INVALID
    }

    // The parsed command from the user input.
    private final Command command;
    // The parsed arguments from the user input.
    private final String argument;
    // The invalid command from the user input. Used to display error message.
    private String invalidCommand = "";

    /**
     * Constructor for a Parser. The input is parsed into a command and arguments.
     *
     * @param userInput The String entered by the user.
     */
    public Parser(String userInput) {
        String[] splitInputs = userInput.split("\\s+");
        // temporary variable to store the parsed command before assigning to final variable
        Command parsedCommand;

        if (splitInputs.length > 1) {
            // the user has input a command with arguments
            try {
                parsedCommand = Command.valueOf(splitInputs[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                parsedCommand = Command.INVALID;
                this.invalidCommand = splitInputs[0];
            }
            this.argument = userInput.substring(splitInputs[0].length() + 1);
        } else {
            // the user has input a command without arguments
            try {
                parsedCommand = Command.valueOf(userInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                parsedCommand = Command.INVALID;
                this.invalidCommand = userInput;
            }
            this.argument = "";
        }
        this.command = parsedCommand;
    }

    /**
     * Returns the Command parsed from the user input.
     *
     * @return The Command parsed from the user input.
     */
    public Command getCommand() {
        return this.command;
    }

    /**
     * Returns the argument parsed from the user input.
     *
     * @return The argument parsed from the user input.
     */
    public String getArgument() {
        return this.argument;
    }

    /**
     * Returns the invalid command parsed from the user input.
     *
     * @return The invalid command parsed from the user input.
     */
    public String getInvalidCommand() {
        return this.invalidCommand;
    }

}
