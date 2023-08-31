import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Parses the input command and performs corresponding actions based on the provided input.
     * This method handles various commands such as listing tasks, marking/unmarking tasks, deleting tasks,
     * and adding new tasks.
     *
     * @param input The input command provided by the user.
     * @throws CCException If an error occurs during parsing or execution of the command.
     */
    public Command parseInput(String input) throws CCException {
        int space = input.indexOf(' ');
        String action = "";
        String taskDescription = "";
        if (space == -1) {
            action = input;
        } else {
            action = input.substring(0, space);
            taskDescription = input.substring(space + 1, input.length());
        }
        return new Command(action, taskDescription);
    }

    public Task parseTask(String type, String input) throws CCException {
        Task task = null;
        switch (type) {
            case "todo":
                task = parseToDo(input);
                break;
            case "deadline":
                task = parseDeadline(input);
                break;
            case "event":
                task = parseEvent(input);
                break;
        }
        return task;
    }

    /**
     * Parses the input string to create a new ToDo task.
     * The method extracts the task description from the input and creates a new ToDo task.
     *
     * @param input The input string containing the todo description.
     *              The input should be in the format "todo todo_description"
     * @return A new ToDo task object created from the provided input.
     * @throws CCException If the input string is empty or if there is an error in task creation.
     */
    private ToDo parseToDo (String taskDescription) throws CCException {
        if (taskDescription.isEmpty()) {
            throw new CCException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new ToDo(taskDescription);
    }

    /**
     * Parses the input string to create a new Deadline task.
     * The method splits the input into fields, checks for the correct format, and extracts the task name
     * and deadline information to create a new Deadline task.
     *
     * @param input The input string containing the deadline description and end time.
     *              The input should be in the format "deadline deadline_description /by end_time"
     * @return A new Deadline task object created from the provided input.
     * @throws CCException If the input format is incorrect or if there are empty fields.
     */
    private Deadline parseDeadline(String taskDescription) throws CCException {
        String[] fields = taskDescription.split("/by");
        if (fields.length != 2) {
            throw new CCException("OOPS!!! Incorrect format for deadline.");
        }
        String name = fields[0].trim();
        String end = fields[1].trim();
        if (name.isEmpty() || end.isEmpty()) {
            throw new CCException("OOPS!!! Empty field for deadline detected.");
        }
        return new Deadline(taskDescription, name, parseDate(end));
    }

    /**
     * Parses the input string to create a new Event task.
     * The method splits the input into fields, checks for the correct format, and extracts the task name,
     * start time, and end time information to create  a new Event task.
     *
     * @param input The input string containing the event description and start and end timings.
     *              The input should be in the format "event event_description /from start_time /to end_time
     * @return A new Event task object created from the provided input.
     * @throws CCException If the input format is incorrect or if there are empty fields.
     */
    private Event parseEvent(String taskDescription) throws CCException{
        String[] fields = taskDescription.split("/from|/to");
        if (fields.length != 3) {
            throw new CCException("OOPS!!! Incorrect format for event.");
        }
        String name = fields[0].trim();
        String start = fields[1].trim();
        String end = fields[2].trim();
        if (name.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new CCException("OOPS!!! Empty field for event detected.");
        }
        return new Event(taskDescription, name, parseDate(start), parseDate(end));
    }

    private static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
        return null;
    }
}
