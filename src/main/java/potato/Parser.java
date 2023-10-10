package potato;

import potato.command.AddCommand;
import potato.command.ClearCommand;
import potato.command.Command;
import potato.command.DeleteCommand;
import potato.command.ExitCommand;
import potato.command.FindCommand;
import potato.command.ListCommand;
import potato.command.MarkCommand;
import potato.command.SetPriorityCommand;
import potato.command.UnmarkCommand;

/**
 * The Parser class is responsible for parsing user input and generating corresponding Command objects.
 * It analyzes user input and determines the appropriate action to take based on the input provided.
 */
public class Parser {

    /**
     * Parses the user input and returns a Command object that represents the action to be executed.
     *
     * @param input The user input to be parsed.
     * @return A Command object representing the action to be performed based on the input.
     */
    public static Command parse(String input) {
        if (input.equals("bye")) {
            return new ExitCommand();

        } else if (input.startsWith("mark ")) {
            return new MarkCommand(input);

        } else if (input.startsWith("unmark ")) {
            return new UnmarkCommand(input);

        } else if (input.startsWith("delete ")) {
            return new DeleteCommand(input);

        } else if (input.startsWith("find ")) {
            return new FindCommand(input);

        } else if (input.startsWith("priority ")) {
            return new SetPriorityCommand(input);

        } else if (input.equals("list")) {
            return new ListCommand();

        } else if (input.equals("clear")) {
            return new ClearCommand();

        } else {
            return new AddCommand(input);
        }
    }
}
