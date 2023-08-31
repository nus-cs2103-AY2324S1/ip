import ip.utils.Pair;

import java.util.Scanner;

public enum CommandType {
    /** Command to exit the program. **/
    BYE ("bye"),
    /** Command to list all tasks in the task list. **/
    LIST ("list"),
    /** Command to add a new to-do task to the task list. **/
    TODO ("todo"),
    /** Command to add a new deadline task to the task list. **/
    DEADLINE ("deadline"),
    /** Command to add a new event task to the task list. **/
    EVENT ("event"),
    /** Command to mark a task to be complete. **/
    MARK ("mark"),
    /** Command to mark a task as incomplete. **/
    UNMARK ("unmark"),
    /** Command to delete a task. **/
    DELETE ("delete"),
    /** Command to denote an unknown keyword call. **/
    UNKNOWN ("");

    /** The String representation of the enum. Used to parse the command into enum. **/
    private final String keyword;

    /**
     * Constructor for the enum Command. <br>
     * Enum constructors are implicitly private, so the tag is not included.
     * @param keyword The keyword of the task.
     */
    CommandType(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Helper function. Splits the console input string into the invoking keyword
     * and its description after the invoking keyword. <br>
     * If no description exists after the keyword, an empty string is returned in the
     * second half of the Pair structure.
     *
     * @param input The unmodified console string that the user inputs.
     * @return A Pair&lt;Command, String&gt; object containing the enum and description.
     */
    public static Pair<CommandType, String> parse(String input) {
        Scanner scanner = new Scanner(input);
        // if the input is empty, return the unknown keyword with an empty description.
        if (!scanner.hasNext()) {
            scanner.close();
            return new Pair<>(CommandType.UNKNOWN, "");
        }

        String keyword = scanner.next();
        CommandType first = CommandType.UNKNOWN;

        for (CommandType command: CommandType.values()) {
            if (keyword.equals(command.keyword)) {
                first = command;
                break;
            }
        }

        if (first.equals(CommandType.UNKNOWN) || !scanner.hasNextLine()) {
            scanner.close();
            return new Pair<>(first, "");
        }

        String second = scanner.nextLine().trim();
        scanner.close();
        return new Pair<>(first, second);
    }
}
