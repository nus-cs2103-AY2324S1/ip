import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Parses the input command and creates a Command object.
     * This method extracts the action and task description from the input and returns a Command object.
     *
     * @param input The input command provided by the user.
     * @return A Command object representing the parsed command.
     * @throws CCException If an error occurs during parsing.
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

    /**
     * Parses a task from a line of data file input and returns the corresponding Task object.
     * This method extracts task details from the input, creates a Command object, and parses the Task type.
     *
     * @param fileLine The input line from the data file containing task details.
     * @return A Task object representing the parsed task.
     * @throws CCException If an error occurs during parsing.
     */
    public Task parseTaskFromFile(String fileLine) throws CCException {
        char done = fileLine.charAt(0);
        String input = fileLine.substring(1, fileLine.length());
        Command command = parseInput(input);
        Task task = parseTask(command.getAction(), command.getTaskDescription());
        if (done == 'X') {
            task.setDone(true);
        }
        return task;
    }

    /**
     * Parses a Task object based on its type and input details.
     *
     * @param type The type of the task (todo, deadline, event).
     * @param input The input containing task details.
     * @return A Task object representing the parsed task.
     * @throws CCException If an error occurs during parsing or task creation.
     */
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
     *
     * @param taskDescription The description of the ToDo task.
     * @return A new ToDo task object created from the provided description.
     * @throws CCException If the description is empty.
     */
    private ToDo parseToDo (String taskDescription) throws CCException {
        if (taskDescription.isEmpty()) {
            throw new CCException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new ToDo(taskDescription);
    }

    /**
     * Parses the input string to create a new Deadline task.
     *
     * @param taskDescription The description and end time of the Deadline task.
     * @return A new Deadline task object created from the provided description.
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
     *
     * @param taskDescription The description, start time, and end time of the Event task.
     * @return A new Event task object created from the provided description.
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


    /**
     * Parses a string into a LocalDate object.
     *
     * @param date The string containing a date to be parsed.
     * @return A LocalDate object representing the parsed date.
     */
    private static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
        return null;
    }
}
