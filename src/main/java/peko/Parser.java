package peko;


import peko.commands.Commands;

/**
 * The `Parser` class is responsible for parsing user input and determining the appropriate
 * command and description for the Peko chat application.
 */
public class Parser {

    private String string;
    private static final String[] commands = new String[]
            {"echo","otsupeko", "list", "write", "mark", "unmark",
                    "todo", "deadline", "event", "find", "delete","tell me a joke"};

    /**
     * Constructs a `Parser` object with the specified user input string.
     *
     * @param s The user input string to be parsed.
     */
    public Parser(String s) {
        this.string = s;
    }

    /**
     * Gets the command associated with the user input.
     *
     * @return The command represented as an enum value from the `Commands` enumeration.
     */
    public Commands getResponseValue() {

        if (string.toLowerCase().equals("tell me a joke")) {
            return Commands.COPYPASTA;
        }
        int output = 0;
        string = string.toLowerCase();
        for (int i = 0; i < commands.length; i++) {
            if (string.startsWith(commands[i])) {
                output = i;
                break;
            }
        }
        String temp = commands[output].toUpperCase().trim();
        return Commands.valueOf(temp);
    }
    /**
     * Gets the description or additional input associated with the user command.
     *
     * @return The description or additional input as a string.
     */
    public String getDescription() {
        return string.split(" ",2).length < 2 ? " " : string.split(" ", 2)[1];
    }
}
