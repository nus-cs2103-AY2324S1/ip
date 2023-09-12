package seedu.duke;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.ErrorCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.MarkCommand;
import seedu.duke.command.UnmarkCommand;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.TaskType;
import seedu.duke.task.Todo;

/**
 * Represents a parser.
 * A parser is responsible for parsing and
 * making sense of user inputs.
 *
 * @author KAM JIA YUE
 * @since 2023-08-29
 */
public class Parser {

    /**
     * The main constructor of this Parser class.
     */
    public Parser() {

    }

    /**
     * Parses user inputs into valid Commands.
     *
     * @param input Input from the user.
     * @return A type of Commands enum to prevent erroneous inputs.
     */
    public Command parse(String input) {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("todo")) {
            return new AddCommand(input, TaskType.TODO);
        } else if (input.startsWith("deadline")) {
            return new AddCommand(input, TaskType.DEADLINE);
        } else if (input.startsWith("event")) {
            return new AddCommand(input, TaskType.EVENT);
        } else if (input.startsWith("find")) {
            return new FindCommand(input);
        }
        return new ErrorCommand();
    }

    /**
     * Marks a task as checked.
     *
     * @param input Input from the user.
     * @return The task index of the task to be marked.
     * @throws DukeException If there are user input errors.
     */
    public int mark(String input) throws DukeException {
        if (input.length() == 4) {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a valid task number to be marked.");
        }
        if (input.charAt(4) != ' ') {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (input.length() == 5) {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a valid task number to be marked.");
        }

        try {
            return Integer.parseInt(input.substring(5)) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a valid task number to be marked.");
        }
    }

    /**
     * Marks a task as unchecked.
     *
     * @param input Input from the user.
     * @return The task index of the task to be unmarked.
     * @throws DukeException If there are user input errors.
     */
    public int unmark(String input) throws DukeException {
        if (input.length() == 6) {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a valid task number to be unmarked.");
        }
        if (input.charAt(6) != ' ') {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (input.length() == 7) {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a valid task number to be unmarked.");
        }

        try {
            return Integer.parseInt(input.substring(7)) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a valid task number to be unmarked.");
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param input Input from the user.
     * @return The task index of the task to be deleted.
     * @throws DukeException If there are user input errors.
     */
    public int delete(String input) throws DukeException {
        if (input.length() == 6) {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a valid task number to be deleted.");
        }
        if (input.charAt(6) != ' ') {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (input.length() == 7) {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a valid task number to be deleted.");
        }

        try {
            return Integer.parseInt(input.substring(7)) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a valid task number to be deleted.");
        }
    }

    /**
     * Instantiates a Todo task.
     *
     * @param input Input from the user.
     * @return A Todo task.
     * @throws DukeException If there are user input errors.
     */
    public Task todo(String input) throws DukeException {
        if (input.length() == 4) {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! The description of a todo cannot be empty.");
        }
        if (input.charAt(4) != ' ') {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (input.length() == 5) {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! The description of a todo cannot be empty.");
        }

        return new Todo(input.substring(5));
    }

    /**
     * Instantiates a Deadline task.
     *
     * @param input Input from the user.
     * @return A Deadline task.
     * @throws DukeException If there are user input errors.
     */
    public Task deadline(String input) throws DukeException {
        try {

            int slashIndex = input.indexOf("/by");

            String by = input.substring(slashIndex + 4);

            return new Deadline(input.substring(9, slashIndex - 1), by);
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! The format of a deadline should be "
                    + "'deadline YOUR_TASK /by DD/MM/YYYY HHmm'.");
        }
    }

    /**
     * Instantiates an Event task.
     *
     * @param input Input from the user.
     * @return An Event task.
     * @throws DukeException If there are user input errors.
     */
    public Task event(String input) throws DukeException {
        if (input.length() == 5) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (input.charAt(5) != ' ') {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (input.length() == 6) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new DukeException("☹ OOPS!!! The format of an event should be " +
                    "'event YOUR_EVENT /from START_TIME /to END_TIME'.");
        }

        try {

            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");


            String from = input.substring(fromIndex + 6, toIndex - 1);


            String to = input.substring(toIndex + 4);

            return new Event(input.substring(6, fromIndex - 1), from, to);
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! The format of an event should be "
                    + "'event YOUR_EVENT /from DD/MM/YYYY HHmm /to DD/MM/YYYY HHmm'.");
        }
    }

    /**
     * Finds the task which the user
     * intends to find.
     *
     * @param input Input of user.
     * @return The String representation
     *      of the task which the user intends
     *      to find.
     * @throws DukeException If there are user input errors.
     */
    public String find(String input) throws DukeException {
        if (input.length() == 4) {
            throw new DukeException("☹ OOPS!!! Please specify a task to find.");
        }
        if (input.charAt(4) != ' ') {
            throw new DukeException("☹ OOPS!!! I'm sorry, " +
                    "but I don't know what that means :-(");
        }
        if (input.length() == 5) {
            throw new DukeException("☹ OOPS!!! Please specify a task to find.");
        }
        String toFind = input.substring(5);
        return toFind;
    }
}
