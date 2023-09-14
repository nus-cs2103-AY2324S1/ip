package duke;

import duke.command.*;

/**
 * Parses user input.
 */
public class CommandParser {

    /**
     * Parses user input into a command for execution.
     * @param input full user input string
     * @return the command based on the user input
     */
    public static Command parse(String input) {
        if (input.equals("bye")) {
            return new ExitCommand();
        }

        if (input.equals("list")) {
            return new ListCommand();
        }

        if (input.startsWith("mark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return new MarkCommand(index);
        }

        if (input.startsWith("unmark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return new UnmarkCommand(index);
        }

        if (input.startsWith("find")) {
            String keyword = input.split(" ")[1];
            return new FindCommand(keyword);
        }

        if (input.startsWith("delete")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return new DeleteCommand(index);
        }

        return new AddCommand(input);
    }
}
