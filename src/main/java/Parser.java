import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Task parseInput (String type, String input) throws CCException {
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
    private static ToDo parseToDo (String input) throws CCException {
        if (input.equals("todo")) {
            throw new CCException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new ToDo(input, input.substring("todo".length()).trim());
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
    private static Deadline parseDeadline(String input) throws CCException {
        String[] fields = input.split("/");
        if (fields.length != 2) {
            throw new CCException("OOPS!!! Incorrect format for deadline.");
        }
        if (!fields[0].startsWith("deadline ") || !fields[1].startsWith("by ")) {
            throw new CCException("OOPS!!! Incorrect format for deadline.");
        }
        String name = fields[0].substring("deadline".length()).trim();
        String end = fields[1].substring("by".length()).trim();
        if (name.isEmpty() || end.isEmpty()) {
            throw new CCException("OOPS!!! Empty field for deadline detected.");
        }
        return new Deadline(input, name, parseDate(end));
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
    private static Event parseEvent(String input) throws CCException{
        String[] fields = input.split("/");
        if (fields.length != 3) {
            throw new CCException("OOPS!!! Incorrect format for event.");
        }
        if (!fields[0].startsWith("event ") || !fields[1].startsWith("from ") || !fields[2].startsWith("to ")) {
            throw new CCException("OOPS!!! Incorrect format for event.");
        }
        String name = fields[0].substring("event".length()).trim();
        String start = fields[1].substring("from".length()).trim();
        String end = fields[2].substring("to".length()).trim();
        if (name.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new CCException("OOPS!!! Empty field for event detected.");
        }
        return new Event(input, name, parseDate(start), parseDate(end));
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
