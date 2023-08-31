public class Parser {

    public static Command parse(String input) throws DukeException {
        String[] parts = input.split(" ", 2);
        String commandType = parts[0];

        switch (commandType) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                int taskIndex2 = Integer.parseInt(input.substring(5)) - 1;
                return new MarkCommand(taskIndex2);
            case "unmark":
                int taskIndex3 = Integer.parseInt(input.substring(7)) - 1;
                return new UnmarkCommand(taskIndex3);
            case "todo":
                if (input.length() <= 5) {
                    throw new DukeException("\t ☹ OOPS!!! The description of a todo cannot be empty.");
                }
                return new AddCommand(new Todo(input.substring(5), false));
            case "deadline":
                // Parse deadline command and create DeadlineTask object
                int byIndex = input.indexOf("/by");
                if (byIndex == -1) {
                    throw new DukeException("\t ☹ OOPS!!! The deadline description must include a /by date.");
                }
                String description = input.substring(9, byIndex).trim();
                if (description.isEmpty()) {
                    throw new DukeException("☹ OOPS!!! The deadline description cannot be empty.");
                }
                String by = input.substring(byIndex + 3).trim();
                return new AddCommand(new Deadline(description, false, by));
            case "event":
                int fromIndex = input.indexOf("/from");
                int toIndex = input.indexOf("/to");
                if (fromIndex == -1 && toIndex == -1) {
                    throw new DukeException("\t ☹ OOPS!!! The event description must include both /from and /to dates.");
                }
                String description2 = input.substring(6, fromIndex).trim();
                if (description2.isEmpty()) {
                    throw new DukeException("☹ OOPS!!! The event description cannot be empty.");
                }
                String from = input.substring(fromIndex + 5, toIndex).trim();
                String to = input.substring(toIndex + 3).trim();
                // Parse event command and create EventTask object
                return new AddCommand(new Event(description2, false, from, to));
            case "delete":
                int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                return new DeleteCommand(taskIndex);
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
