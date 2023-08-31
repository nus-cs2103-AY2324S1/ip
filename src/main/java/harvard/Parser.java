package harvard;
/**
 * Represents a parser that parses user input.
 */
public class Parser {
    /**
     * Parses the user input.
     *
     * @param command The user input.
     * @return The command.
     * @throws DukeException If there is an error parsing the user input.
     */
    public static Command parse(String command) throws DukeException {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.startsWith("todo")) {
            // Parse and return a TodoCommand
            if (command.length() < 5) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String description = command.substring(5);
            if (description.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            Todo todo = new Todo(description);
            return new AddCommand(todo);
        } else if (command.startsWith("deadline")) {
            // Parse and return a DeadlineCommand
            if (command.length() < 9) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            if (!command.contains(" /by ")) {
                throw new DukeException("☹ OOPS!!! The deadline must be specified.");
            }
            String[] split = command.split(" /by ");
            if (split[0].length() < 9) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            if (split.length != 2) {
                throw new DukeException("☹ OOPS!!! The deadline must be specified.");
            }
            String description = split[0].substring(9);
            if (description.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            String by = split[1];
            if (by.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The deadline must be specified.");
            }
            Deadline deadline = new Deadline(description, by);
            return new AddCommand(deadline);
        } else if (command.startsWith("event")) {
            // Parse and return an EventCommand
            if (command.length() < 6) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            if (!command.contains(" /from ") || !command.contains(" /to ")) {
                throw new DukeException("☹ OOPS!!! The event time must be specified.");
            }
            String[] split = command.split("/from ");
            if (split[0].length() < 6) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            String[] split2 = split[1].split(" /to ");
            if (split2.length != 2) {
                throw new DukeException("☹ OOPS!!! The event time must be specified.");
            }
            String description = split[0].substring(6);
            if (description.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            String from = split2[0];
            String to = split2[1];
            if (from.isEmpty() || to.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The event time must be specified.");
            }
            if (from.equals(to)) {
                throw new DukeException("☹ OOPS!!! The event start time and end time cannot be the same.");
            }
            if (from.compareTo(to) > 0) {
                throw new DukeException("☹ OOPS!!! The event start time cannot be later than the end time.");
            }
            Event event = new Event(description, from, to);
            return new AddCommand(event);
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("delete")) {
            // Parse and return a DeleteCommand
            String[] split = command.split(" ");
            if (split.length != 2) {
                throw new DukeException("☹ OOPS!!! The index must be specified.");
            }
            int index = Integer.parseInt(split[1]) - 1;
            return new DeleteCommand(index);
        } else if (command.startsWith("mark")) {
            // Parse and return a MarkCommand
            String[] split = command.split(" ");
            int index = Integer.parseInt(split[1]) - 1;
            return new MarkCommand(index);
        } else if (command.startsWith("unmark")) {
            // Parse and return an UnmarkCommand
            String[] split = command.split(" ");
            int index = Integer.parseInt(split[1]) - 1;
            return new UnmarkCommand(index);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}