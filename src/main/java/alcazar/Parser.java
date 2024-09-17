package alcazar;

import alcazar.commands.AddDeadlineCommand;
import alcazar.commands.AddEventCommand;
import alcazar.commands.AddToDoCommand;
import alcazar.commands.ChangeDataSource;
import alcazar.commands.Command;
import alcazar.commands.DeleteCommand;
import alcazar.commands.ExitCommand;
import alcazar.commands.FindCommand;
import alcazar.commands.ListCommand;
import alcazar.commands.MarkCommand;
import alcazar.commands.UnmarkCommand;
import alcazar.exceptions.AlcazarException;
import alcazar.exceptions.InvalidArgumentException;
import alcazar.exceptions.InvalidSerialException;
import alcazar.exceptions.InvalidTaskException;


/**
 * Encapsulates functionality to parse the input commands
 */
public class Parser {

    /**
     * Interprets the command input.
     * @param prompt The input text
     * @return The Command representing the input prompt
     * @throws InvalidArgumentException In the situation when an InvalidArgument is passed
     * @throws InvalidTaskException In the situation when an unrecognized task was passed
     * @throws InvalidSerialException In the situation where an invalid serial number is passed
     */
    public static Command parse(String prompt) throws AlcazarException {

        if (prompt.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (prompt.contains("bye")) {
            return new ExitCommand();
        } else if (prompt.contains("find")) {
            return new FindCommand(prompt);
        } else if (prompt.contains("unmark")) {
            return new UnmarkCommand(prompt);
        } else if (prompt.contains("mark")) {
            return new MarkCommand(prompt);
        } else if (prompt.contains("delete")) {
            return new DeleteCommand(prompt);
        } else if (prompt.contains("deadline")) {
            return new AddDeadlineCommand(prompt);
        } else if (prompt.contains("event")) {
            return new AddEventCommand(prompt);
        } else if (prompt.contains("todo")) {
            return new AddToDoCommand(prompt);
        } else if (prompt.contains("data source")) {
            return new ChangeDataSource(prompt);
        } else {
            throw new InvalidTaskException(
                    "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
            );
        }

    }
}
