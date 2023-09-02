package duke;

/**
 * Deals with making sense of user commands.
 */
public class Parser {

    /**
     * Returns the command inputted.
     *
     * @param input Input entered by the user.
     * @return Command entered by the user.
     * @throws DukeMissingArgumentException If command entered is invalid.
     */
    public static String parseCommand(String input) throws DukeInvalidCommandException {
        String[] strArr;
        try {
            strArr = input.split(" ", 2);
            return strArr[0];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException(input);
        }
    }

    /**
     * Returns the information other than command inputted.
     *
     * @param input Input entered by the user.
     * @return Information other than command entered by the user.
     * @throws DukeMissingArgumentException If no information is found.
     */
    public static String parseInfo(String input) throws DukeMissingArgumentException {
        try {
            String[] strArr = input.split(" ", 2);
            return strArr[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }
}
