package duke.parser;

import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkAsDoneCommand;
import duke.command.UnmarkAsDoneCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a parser that parses user input into specific commands.
 */
public class Parser {
    /**
     * Parses the user input into a specific command.
     *
     * @param fullCommand The entire user input.
     * @return The command parsed from the user input.
     * @throws DukeException If the user input is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] inputs = fullCommand.split(" ", 2);

        String command = inputs[0].toUpperCase();
        String argument = inputs.length == 2 ? inputs[1] : "";

        try {
            switch (CommandKeyword.valueOf(command)) {
            case LIST:
                return new ListCommand();
            case MARK:
                try {
                    int index = Integer.parseInt(argument) - 1;
                    return new MarkAsDoneCommand(index);
                } catch (NumberFormatException e) {
                    throw new DukeException("\u2639 OOPS!!! The index of a task must be a number.");
                }
            case UNMARK:
                try {
                    int index = Integer.parseInt(argument) - 1;
                    return new UnmarkAsDoneCommand(index);
                } catch (NumberFormatException e) {
                    throw new DukeException("\u2639 OOPS!!! The index of a task must be a number.");
                }
            case DELETE:
                try {
                    int index = Integer.parseInt(argument) - 1;
                    return new DeleteCommand(index);
                } catch (NumberFormatException e) {
                    throw new DukeException("\u2639 OOPS!!! The index of a task must be a number.");
                }
            case BYE:
                return new ExitCommand();
            case TODO: {
                String description = argument;
                Task task = new Todo(description);
                return new AddCommand(task);
            }
            case DEADLINE:
                try {
                    inputs = argument.split(" /", 2);
                    String description = inputs[0];
                    String by = inputs[1].split(" ", 2)[1];
                    Task task = new Deadline(description, by);
                    return new AddCommand(task);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("\u2639 OOPS!!! The deadline of a deadline cannot be empty.");
                } catch (DateTimeParseException e) {
                    throw new DukeException(
                        "\u2639 OOPS!!! The date time format must be something like 2007-12-03T10:15:30.");
                }
            case EVENT:
                try {
                    inputs = argument.split(" /", 3);
                    String description = inputs[0];
                    String from = inputs[1].split(" ", 2)[1];
                    String to = inputs[2].split(" ", 2)[1];
                    Task task = new Event(description, from, to);
                    return new AddCommand(task);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException(
                        "\u2639 OOPS!!! The start time or end time of an event cannot be empty.");
                } catch (DateTimeParseException e) {
                    throw new DukeException(
                        "\u2639 OOPS!!! The date time format must be something like 2007-12-03T10:15:30.");
                }
            case FIND:
                return new FindCommand(argument);
            default:
                return new Command();
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private enum CommandKeyword {
        LIST, MARK, UNMARK, DELETE, BYE, TODO, DEADLINE, EVENT, FIND
    }
}
