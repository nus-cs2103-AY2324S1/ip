package duke;

/**
 * Represents the object that parses user inputs.
 */
public class Parser {
    /**
     * Returns the command type of the given user input.
     * @param line The user input
     * @return The command type of the input
     * @throws DukeException if user gives an unknown command
     */
    public static CommandType parseCommand(String line) throws DukeException {
        String command = line.split(" ")[0];
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("!!!: Sorry I do not understand what you mean");
        }
    }

    /**
     * Returns the options of the given user input.
     * @param line The user input
     * @return The options of the command from user input
     * @throws DukeException if user does not give any options
     */
    public static String parseOptions(String line) throws DukeException {
        String command = line.split(" ", 2)[1];
        try {
            return command;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Input is not complete");
        }
    }
}
