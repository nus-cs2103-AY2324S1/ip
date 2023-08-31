package miles;

import miles.command.AddDeadlineCommand;
import miles.command.AddToDoCommand;
import miles.command.AddEventCommand;
import miles.command.Command;
import miles.command.DeleteCommand;
import miles.command.ExitCommand;
import miles.command.FindCommand;
import miles.command.ListCommand;
import miles.command.MarkCommand;
import miles.command.UnmarkCommand;

/**
 * Represents the parsing of user input, making sense of what command the user has given.
*/
public class Parser {
    /**
     * Parses the input that the user gives.
     * 
     * @param input  user input
     * @return       a Command object that corresponds to the user input
    */
    public Command parse(String input) throws MilesException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.contains("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.contains("mark")) {
            return new MarkCommand(input);
        } else if (input.contains("todo")) {
            return new AddToDoCommand(input);
        } else if (input.contains("deadline")) {
            return new AddDeadlineCommand(input);
        } else if (input.contains("event")) {
            return new AddEventCommand(input);
        } else if (input.contains("delete")) {
            return new DeleteCommand(input);
        } else if (input.contains("find")) {
            return new FindCommand(input);
        } else if (input.equals("")) {
            String errorMsg = "Input a task my brother.";
            throw new MilesException(errorMsg);
        } else {
            String errorMsg = "I'm sorry brother, I do not have a Scooby :-(";
            throw new MilesException(errorMsg);
        }
    }
}
