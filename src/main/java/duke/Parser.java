
package duke;


/**
 * The `Parser` class is responsible for parsing user commands and extracting relevant information from them.
 * It helps identify the type of command and retrieves any associated details.
 */
public class Parser {

    /**
     * Parses the command type from the user input string.
     *
     * @param input The user input string containing the command.
     * @return The CommandType enum representing the parsed command.
     * @throws DukeException If the input does not match any valid command type.
     */
    public static CommandType parseCommand(String input) throws DukeException {
        String command = input.split(" ", 2)[0].toUpperCase();
        try {
            return CommandType.valueOf(command);
        } catch (IllegalArgumentException e) {
            throw new DukeException("Oops, I don't understand the command.");
        }
    }

    /**
     * Parses the relevant content from a user input string.
     *
     * @param input The user input string containing the command and content.
     * @return The content of the command, excluding the command type.
     * @throws DukeException If the input does not contain the expected content.
     */
    public static String doWhat(String input) throws DukeException {
        try {
            return input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The command is empty.");
        }
    }
}
