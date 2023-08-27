/**
 * Handles user input and convert them into the right command.
 */
public class Parser {
    public static Command parse(String nextLine) {
        if (nextLine.equals("list")) {
            return new DisplayCommand();
        } else if (nextLine.contains("mark")) { // if command is to mark or unmark
            return new MarkCommand(nextLine);
        } else if (nextLine.contains("delete")) {
            return new DeleteCommand(nextLine);
        } else if (nextLine.equals("bye")) {
            return new ExitCommand();
        } else { // if command is to add tasks
            return new AddCommand(nextLine);
        }
    }
}
