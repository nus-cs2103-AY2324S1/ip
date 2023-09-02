package fishron;

/**
 * The Parser class is responsible for parsing user input and converting it into executable commands.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input    The user input string.
     * @param taskList The TaskList to operate on.
     * @return A Command object representing the parsed command.
     * @throws FishronException If there is an issue with parsing or an invalid command.
     */
    public static Command parse(String input, TaskList taskList) throws FishronException {

        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        }

        isValidCommands(input, taskList);

        if (input.equalsIgnoreCase("list")) {
            return new ListCommand();

        } else if (input.toLowerCase().startsWith("mark")) {
            int taskIndex = Integer.parseInt(input.split(" ")[1]);
            return new MarkDoneCommand(taskIndex);

        } else if (input.toLowerCase().startsWith("unmark")) {
            int taskIndex = Integer.parseInt(input.split(" ")[1]);
            return new UnmarkCommand(taskIndex);

        } else if (input.toLowerCase().startsWith("delete")) {
            int taskIndex = Integer.parseInt(input.split(" ")[1]);
            return new DeleteCommand(taskIndex);

        } else if (input.toLowerCase().startsWith("todo")) {
            String[] parts = input.split("todo");
            return new AddCommand(parseTodo(parts[1]));

        } else if (input.toLowerCase().startsWith("deadline")) {
            String[] parts = input.split("/by");
            return new AddCommand(parseDeadline(parts[0].split("deadline")[1].trim(), parts[1].trim()));

        } else if (input.toLowerCase().startsWith("event")) {
            String[] parts = input.split("/from|/to");
            return new AddCommand(parseEvent(parts[0].split("event")[1].trim(), parts[1].trim(), parts[2].trim()));
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return new ListCommand();
    }

    /**
     * Validates whether the given command is valid and follows the required format.
     *
     * @param command  The command to validate.
     * @param taskList The TaskList to validate against.
     * @return True if the command is valid, otherwise throws an exception.
     * @throws FishronException If the command is invalid or in an incorrect format.
     */
    public static boolean isValidCommands(String command, TaskList taskList) throws FishronException{
        if (command.equals("list")) {
            return true;
        }

        if (command.toLowerCase().startsWith("mark") && command.split("mark").length <= 1) {
            throw new FishronException("☹ OOPS!!! Please provide a task to be marked.");
        }

        if (command.toLowerCase().startsWith("unmark") && command.split("unmark").length <= 1) {
            throw new FishronException("☹ OOPS!!! Please provide a task to be unmarked.");
        }

        if (command.toLowerCase().startsWith("mark") || command.toLowerCase().startsWith("unmark") ||
        command.toLowerCase().startsWith("delete")) {
            String taskIndexPart = command.split(" ")[1].trim();
            try {
                Integer.parseInt(taskIndexPart);
            } catch (NumberFormatException e) {
                throw new FishronException("☹ OOPS!!! Please provide a valid task index.");
            }
            if (Integer.parseInt(taskIndexPart) < 1 || Integer.parseInt(taskIndexPart) > taskList.getSize()) {
                throw new FishronException("☹ OOPS!!! Please provide a valid task index.");
            }
        }

        if (command.toLowerCase().startsWith("todo") && command.split("todo").length <= 1) {
            throw new FishronException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        if (command.toLowerCase().startsWith("deadline") && command.split("deadline").length <= 1) {
            throw new FishronException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        if (command.toLowerCase().startsWith("deadline")) {
            String[] parts = command.split("/by");
            try {
                String emptyDesc = parts[0].split("deadline")[1];
            } catch (Exception e) {
                throw new FishronException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
        }

        if (command.toLowerCase().startsWith("deadline") &&
                (command.split("/by").length != 2 || command.split("/by")[1].trim().isEmpty())) {
            throw new FishronException("☹ OOPS!!! Please provide a valid deadline format.");
        }

        if (command.toLowerCase().startsWith("event") && command.split("event").length <= 1) {
            throw new FishronException("☹ OOPS!!! The description of an event cannot be empty.");
        }

        if (command.toLowerCase().startsWith("event")) {
            String[] parts = command.split("/from|/to");
            try {
                String emptyDesc = parts[0].split("event")[1];
            } catch (Exception e) {
                throw new FishronException("☹ OOPS!!! The description of an event cannot be empty.");
            }
        }

        if (command.toLowerCase().startsWith("event") &&
                (command.split("/from|/to").length != 3 || command.split("/from|/to")[1].trim().isEmpty() ||
                        command.split("/from|/to")[2].trim().isEmpty())) {

            throw new FishronException("☹ OOPS!!! Please provide a valid event format.");
        }
        return true;
    }

    /**
     * Parses a description string and creates a ToDo object.
     *
     * @param description The description of the ToDo task.
     * @return A ToDo object.
     */
    public static ToDo parseTodo(String description) {
        return new ToDo(description);
    }

    /**
     * Parses a description and a deadline string and creates a Deadline object.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline in the format "dd-MM-yyyy HHmm".
     * @return A Deadline object.
     * @throws FishronException If the deadline format is invalid.
     */
    public static Deadline parseDeadline(String description, String by) throws FishronException {
        return new Deadline(description, by);
    }

    /**
     * Parses a description, a "from" string, and a "to" string and creates an Event object.
     *
     * @param description The description of the event task.
     * @param from        The starting date and time in the format "dd-MM-yyyy HHmm".
     * @param to          The ending date and time in the format "dd-MM-yyyy HHmm".
     * @return An Event object.
     * @throws FishronException If the date/time format is invalid.
     */
    public static Event parseEvent(String description, String from, String to) throws FishronException {
        return new Event(description, from, to);
    }
}