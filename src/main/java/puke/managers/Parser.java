package puke.managers;

import puke.command.ClearCommand;
import puke.command.Command;
import puke.command.DeadlineCommand;
import puke.command.DeleteCommand;
import puke.command.ErrorCommand;
import puke.command.EventCommand;
import puke.command.ExitCommand;
import puke.command.FindCommand;
import puke.command.ListCommand;
import puke.command.MarkCommand;
import puke.command.TodoCommand;
import puke.command.UnmarkCommand;

/**
 * A class that takes in input and parses it to return a command.
 */
public class Parser {
    /**
     * Parses the command string as input from the UI and returns its corresponding command
     *
     * @param command First token of input from the UI
     * @param line Remaining input from the UI on the same line
     * @return Corresponding command
     * @throws PukeException If an invalid command or line is parsed
     */
    public static Command parse(String command, String line) throws PukeException {
        try {
            if (command.equals("bye")) {
                return new ExitCommand(line);
            } else if (command.equals("list")) {
                return new ListCommand(line);
            } else if (command.equals("mark")) {
                return new MarkCommand(line.substring(1));
            } else if (command.equals("unmark")) {
                return new UnmarkCommand(line.substring(1));
            } else if (command.equals("todo")) {
                return new TodoCommand(line.substring(1));
            } else if (command.equals("deadline")) {
                return new DeadlineCommand(line.substring(1));
            } else if (command.equals("event")) {
                return new EventCommand(line.substring(1));
            } else if (command.equals("delete")) {
                return new DeleteCommand(line.substring(1));
            } else if (command.equals("clearall")) {
                return new ClearCommand(line);
            } else if (command.equals("find")) {
                return new FindCommand(line.substring(1));
            } else {
                return new ErrorCommand();
            }
        } catch (Exception e) {
            return new ErrorCommand();
        }
    }
}
