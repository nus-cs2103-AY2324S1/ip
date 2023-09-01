package dukeutilities;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;

import exceptions.DukeException;

import task.Deadline;
import task.Event;
import task.ToDo;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.isEmpty()) {
            throw new DukeException("You entered nothing! Try again!");
        } else if (fullCommand.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("find")) {
            return new FindCommand(fullCommand);
        } else if (fullCommand.startsWith("todo")) {
            if (fullCommand.equals("todo")) {
                throw new DukeException("Description cannot be empty!");
            } else {
                ToDo newTodo = new ToDo(fullCommand);
                return new AddCommand(newTodo);
            }
        } else if (fullCommand.startsWith("deadline")) {
            if (fullCommand.equals("deadline")) {
                throw new DukeException("Description cannot be empty!");
            } else {
                try {
                    Deadline newDeadline = new Deadline(fullCommand);
                    return new AddCommand(newDeadline);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please specify the deadline!");
                }
            }
        } else if (fullCommand.startsWith("event")) {
            if (fullCommand.equals("event")) {
                throw new DukeException("Description cannot be empty!");
            } else {
                try {
                    Event newEvent = new Event(fullCommand);
                    return new AddCommand(newEvent);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please specify both the start and end time!");
                }
            }
        } else if (fullCommand.startsWith("delete")) {
            try {
                return new DeleteCommand(fullCommand);
            } catch (NumberFormatException e) {
                throw new DukeException("Please input the correct command!");
            }
        } else if (fullCommand.startsWith("mark")) {
            try {
                return new MarkCommand(fullCommand);
            } catch (NumberFormatException e) {
                throw new DukeException("Please input the correct command!");
            }
        } else if (fullCommand.startsWith("unmark")) {
            try {
                return new UnmarkCommand(fullCommand);
            } catch (NumberFormatException e) {
                throw new DukeException("Please input the correct command!");
            }
        } else if (fullCommand.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else {
            throw new DukeException("Sorry! Don't know what that is!");
        }
    }
}
