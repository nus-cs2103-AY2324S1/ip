package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SortCommand;
import duke.command.UnmarkCommand;

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

        if (input.startsWith("sort")) {
            return new SortCommand();
        }

        return new AddCommand(input);
    }
}
