package seedu.duke;

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
    public Commands parse(String input) {
        if (input.equals("bye")) {
            return Commands.BYE;
        } else if (input.equals("list")) {
            return Commands.LIST;
        } else if (input.startsWith("mark")) {
            return Commands.MARK;
        } else if (input.startsWith("unmark")) {
            return Commands.UNMARK;
        } else if (input.startsWith("delete")) {
            return Commands.DELETE;
        } else if (input.startsWith("todo")) {
            return Commands.TODO;
        } else if (input.startsWith("deadline")) {
            return Commands.DEADLINE;
        } else if (input.startsWith("event")) {
            return Commands.EVENT;
        } else if (input.startsWith("find")) {
            return Commands.FIND;
        }
        return Commands.DEFAULT;
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
        if (input.length() == 8) {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (input.charAt(8) != ' ') {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (input.length() == 9) {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (!input.contains("/by")) {
            throw new DukeException(Ui.I5
                    + "☹ OOPS!!! The format of a deadline should be 'deadline YOUR_TASK /by YOUR_DEADLINE'.");
        }

        int slashIndex = input.indexOf("/by");

        if (input.length() < slashIndex + 4) {
            throw new DukeException(Ui.I5
                    + "☹ OOPS!!! The format of a deadline should be 'deadline YOUR_TASK /by YOUR_DEADLINE'.");
        }

        String by = input.substring(slashIndex + 4);

        if (slashIndex <= 9) {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! The title of your deadline cannot be empty.");
        }

        return new Deadline(input.substring(9, slashIndex - 1), by);
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
            throw new DukeException(Ui.I5 + "☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (input.charAt(5) != ' ') {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (input.length() == 6) {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new DukeException(Ui.I5
                    + "☹ OOPS!!! The format of an event should be 'event YOUR_EVENT /from START_TIME /to END_TIME'.");
        }

        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");

        if (input.charAt(fromIndex + 5) != ' ') {
            throw new DukeException(Ui.I5
                    + "☹ OOPS!!! The format of an event should be 'event YOUR_EVENT /from START_TIME /to END_TIME'.");
        }
        if (fromIndex >= toIndex) {
            throw new DukeException(Ui.I5
                    + "☹ OOPS!!! The format of an event should be 'event YOUR_EVENT /from START_TIME /to END_TIME'.");
        }
        if (toIndex - 1 - (fromIndex + 6) < 1) {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! The starting time of an event should not be empty.");
        }

        String from = input.substring(fromIndex + 6, toIndex - 1);

        if (input.length() < toIndex + 3) {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! The ending time of an event should not be empty.");
        }
        if (input.charAt(toIndex + 3) != ' ') {
            throw new DukeException(Ui.I5
                    + "☹ OOPS!!! The format of an event should be 'event YOUR_EVENT /from START_TIME /to END_TIME'.");
        }

        String to = input.substring(toIndex + 4);

        if (fromIndex - 1 - 6 < 1) {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! The title of an event should not be empty.");
        }
        if (input.charAt(fromIndex - 1) != ' ') {
            throw new DukeException(Ui.I5
                    + "☹ OOPS!!! The format of an event should be 'event YOUR_EVENT /from START_TIME /to END_TIME'.");
        }

        return new Event(input.substring(6, fromIndex - 1), from, to);
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
            throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a task to find.");
        }
        if (input.charAt(4) != ' ') {
            throw new DukeException(Ui.I5
                    + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (input.length() == 5) {
            throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a task to find.");
        }
        String toFind = input.substring(5);
        return toFind;
    }
}
