public class Parser {

    public static Command parse(String userInput) throws DukeException{
        if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.contains("mark") && userInput.substring(0, 4).equals("mark")) {
            if (!userInput.equals("mark")) {
                try {
                    int index = Integer.parseInt(userInput.substring(5)) - 1;
                    return new MarkCommand(index);
                } catch (NumberFormatException e) {
                    throw new DukeException("OOPS!!! Invalid task to be marked!");
                }
            } else {
                throw new DukeException("OOPS!!! Invalid task to be marked!");
            }
        } else if (userInput.contains("unmark") && userInput.substring(0, 6).equals("unmark")) {
            if (!userInput.equals("unmark")) {
                try {
                    int index = Integer.parseInt(userInput.substring(7)) - 1;
                    return new UnmarkCommand(index);
                } catch (NumberFormatException e) {
                    throw new DukeException("OOPS!!! Invalid task to be marked!");
                }
            } else {
                throw new DukeException("OOPS!!! Invalid task to be unmarked!");
            }
        } else if (userInput.contains("delete") && userInput.substring(0, 6).equals("delete")) {
            if (!userInput.equals("delete")) {
                int index = Integer.parseInt(userInput.substring(7)) - 1;
                return new DeleteCommand(index);
            } else {
                throw new DukeException("OOPS!!! Invalid index to be deleted!");
            }
        } else {
            if (userInput.contains("todo") && userInput.substring(0, 4).equals("todo")) {
                // Add a task
                if (userInput.equals("todo")) { // checks if description is empty
                    throw new DukeException("OOPS!!! The description of todo cannot be empty.");
                } else {
                    return new AddCommand(userInput, 'T');
                }
            } else if (userInput.contains("deadline") && userInput.substring(0, 8).equals("deadline")) {
                // Add a deadline
                if (userInput.equals("deadline") || !userInput.contains("/by")) { // checks if description is invalid
                    throw new DukeException("OOPS!!! The description of deadline cannot be empty.");
                } else {
                    return new AddCommand(userInput, 'D');
                }
            } else if (userInput.contains("event") && userInput.substring(0, 5).equals("event")) {
                // Add an event
                if (userInput.equals("event") || !userInput.contains("/from") || !userInput.contains("/to")) { // checks if description is invalid
                    throw new DukeException("OOPS!!! The description of event cannot be empty.");
                } else {
                    return new AddCommand(userInput, 'E');
                }
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
