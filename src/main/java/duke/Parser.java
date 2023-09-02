package duke;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.MissingIndexException;
import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;


/**
 * The duke.Parser class takes an input and returns
 * a corresponding command.
 *
 * @author Zi Xiang
 * @version CS2103 AY23/24 Sem 1
 */
public class Parser {
    // Initialisation of objects and variables
    private static List<String> commands = Arrays.asList(
            new String[]{"todo", "deadline", "event", "mark", "unmark", "delete"});

    /**
     * Returns a corresponding Command from input.
     *
     * @param fullCommand a String with the command
     * @return A Command to be executed
     * @throws DukeException If input does not match
     */
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else {
            String[] temp = fullCommand.split(" ", 2);
            String command = temp[0];
            if (commands.contains(command)) {
                if (temp.length == 1 || temp[1].length() == 0) {
                    if (command.equals("mark") || command.equals("unmark") || command.equals("delete")) {
                        throw new MissingIndexException();
                    } else {
                        throw new EmptyDescriptionException();
                    }
                } else {
                    if (command.equals("mark")) {
                        int index = Integer.parseInt(temp[1]);
                        return new MarkCommand(index);
                    } else if (command.equals("unmark")) {
                        int index = Integer.parseInt(temp[1]);
                        return new UnmarkCommand(index);
                    } else if (command.equals("todo")) {
                        String todo = temp[1];
                        return new AddCommand(new Todo(todo));
                    } else if (command.equals("deadline")) {
                        String[] items = temp[1].split(" /");
                        if (items.length == 1) {
                            throw new EmptyDescriptionException();
                        } else if (!items[1].startsWith("by ")) {
                            throw new UnknownCommandException();
                        } else {
                            if (items[1].length() == 3) {
                                throw new EmptyDescriptionException();
                            } else {
                                LocalDate date = LocalDate.parse(items[1].substring(3));
                                return new AddCommand(new Deadline(items[0], date));
                            }
                        }
                    } else if (command.equals("event")) {
                        String[] items = temp[1].split(" /");
                        if (items.length == 3) {
                            if (items[1].startsWith("from ") && items[2].startsWith("to ")) {
                                return new AddCommand(new Event(items[0], items[1].substring(5),
                                        items[2].substring(3)));
                            } else {
                                throw new UnknownCommandException();
                            }
                        } else {
                            throw new UnknownCommandException();
                        }
                    } else if (command.equals("delete")) {
                        int index = Integer.parseInt(temp[1]);
                        return new DeleteCommand(index);
                    } else if (command.equals("find")) {
                        return new FindCommand(temp[1]);
                    }
                }
            } else {
                return new UnknownCommand();
            }
        }
        return new UnknownCommand();
    }
}
