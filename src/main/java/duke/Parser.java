package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

/**
 * Represents a parser to process commands entered into the chatbot and returns the corresponding Command.
 */
public class Parser {

    /**
     * Parses the message to be processed into a Command, then returns that Command.
     *
     * @param message The message to be parsed.
     * @return The Command parsed from the given message.
     * @throws DukeException If there is an invalid input message causing an error in parsing.
     */
    public static Command parse(String message) throws DukeException {
        if (message.equals("bye")) {
            return new ExitCommand();
        } else if (message.equals("list")) {
            return new ListCommand();
        } else if (message.startsWith("mark")) {
            if (message.length() <= 5) {
                throw new DukeException("You need to specify the index of the task to mark.");
            }
            try {
                return new MarkCommand(Integer.parseInt(message.substring(5)));
            } catch (NumberFormatException e) {
                throw new DukeException("The index of the task to mark is not a valid integer.");
            }
        } else if (message.startsWith("unmark")) {
            if (message.length() <= 7) {
                throw new DukeException("You need to specify the index of the task to unmark.");
            }
            try {
                return new UnmarkCommand(Integer.parseInt(message.substring(7)));
            } catch (NumberFormatException e) {
                throw new DukeException("The index of the task to unmark is not a valid integer.");
            }
        } else if (message.startsWith("find")) {
            if (message.length() <= 5) {
                throw new DukeException("You need to specify the keyword to find the tasks.");
            }
            return new FindCommand(message.substring(5));
        } else if (message.startsWith("delete")) {
            if (message.length() <= 7) {
                throw new DukeException("You need to specify the index of the task to delete.");
            }
            try {
                return new DeleteCommand(Integer.parseInt(message.substring(7)));
            } catch (NumberFormatException e) {
                throw new DukeException("The index of the task to delete is not a valid integer.");
            }
        } else if (message.startsWith("todo")) {
            if (message.length() <= 5) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return new AddCommand(message.substring(5));
        } else if (message.startsWith("deadline")) {
            if (message.length() <= 9) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String[] deadline = message.substring(9).split(" /by ");
            if (deadline.length != 2) {
                throw new DukeException("A deadline requires exactly 1 due date.");
            }
            return new AddCommand(deadline[0], deadline[1]);
        } else if (message.startsWith("event")) {
            if (message.length() <= 6) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            String[] event = message.substring(6).split(" /to | /from ");
            if (event.length != 3) {
                throw new DukeException("An event requires exactly 2 from/to dates.");
            }
            return new AddCommand(event[0], event[1], event[2]);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
