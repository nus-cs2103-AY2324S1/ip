package duke.parser;

import duke.command.*;
import duke.data.exception.DukeException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The Parser class is responsible for parsing user input and converting it into commands and task objects.
 * It supports the creation of ToDo, Deadline, and Event tasks from user input.
 * This class also handles parsing of task details from data files.
 */
public class Parser {

    /**
     * Parses the input command and creates a Command object.
     * This method extracts the action and task description from the input and returns a Command object.
     *
     * @param input The input command provided by the user.
     * @return A Command object representing the parsed command.
     * @throws DukeException If an error occurs during parsing, such as an empty input.
     */
    public Command parseInput(String input) throws DukeException {
        String action = getActionFromInput(input);
        String description = getDescriptionFromInput(input, action);

        assert action != null : "Action should not be null";
        assert description != null : "Description should not be null";

        switch (action) {
        case "todo":
        case "deadline":
        case "event":
            return new AddTaskCommand(parseTask(action, description));
        case "list":
            return new PrintListCommand();
        case "mark":
            return new MarkTaskCommand(description);
        case "unmark":
            return new UnmarkTaskCommand(description);
        case "delete":
            return new DeleteTaskCommand(description);
        case "find":
            return new FindTaskCommand(description);
        case "completed":
            return new CountCompletedTasksCommand();
        case "uncompleted":
            return new CountUncompletedTasksCommand();
        case "stats":
            return new PrintStatsCommand();
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :<");
        }
    }

    private String getActionFromInput(String input) throws DukeException {
        if (input.trim().isEmpty()) {
            throw new DukeException("Empty input detected.");
        }
        String[] words = input.split(" ");
        return words[0];
    }

    private String getDescriptionFromInput(String input, String action) {
        int startIndex = input.indexOf(action + " ");
        if (startIndex >= 0) {
            startIndex += action.length() + 1; // Add 1 to account for the space
            return input.substring(startIndex);
        } else {
            return input; // Return the entire input if the action is not found
        }
    }

    /**
     * Parses a task from a line of data file input and returns the corresponding Task object.
     * This method extracts task details from the input, creates a Command object, and parses the task type.
     *
     * @param fileLine The input line from the data file containing task details.
     * @return A Task object representing the parsed task.
     * @throws DukeException If an error occurs during parsing.
     */
    public Task parseTaskFromFile(String fileLine) throws DukeException {
        String input = fileLine.substring(1); //remove completion status character to extract task input
        String action = getActionFromInput(input);
        String description = getDescriptionFromInput(input, action);
        Task task = parseTask(action, description);
        //Extract the task completion status character from the file line.
        //If it is marked as completed ('X'), set the task as done.
        if (fileLine.charAt(0) == 'X') {
            task.setDone(true);
        }
        return task;
    }

    /**
     * Parses a Task object based on its type and input details.
     *
     * @param type  The type of the task (todo, deadline, event).
     * @param input The input containing task details.
     * @return A Task object representing the parsed task.
     * @throws DukeException If an error occurs during parsing or task creation.
     */
    public Task parseTask(String type, String input) throws DukeException {
        switch (type) {
        case "todo":
            return parseToDo(input);
        case "deadline":
            return parseDeadline(input);
        case "event":
            return parseEvent(input);
        default:
            throw new DukeException("Invalid task type.");
        }
    }

    /**
     * Parses the input string to create a new ToDo task.
     *
     * @param taskDescription The description of the ToDo task.
     * @return A new ToDo task object created from the provided description.
     * @throws DukeException If the description is empty.
     */
    private ToDo parseToDo (String taskDescription) throws DukeException {
        if (taskDescription.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }

        assert taskDescription != null : "TaskDescription should not be null";

        return new ToDo(taskDescription);
    }

    /**
     * Parses the input string to create a new Deadline task.
     *
     * @param taskDescription The description and due date of the Deadline task.
     * @return A new Deadline task object created from the provided description.
     * @throws DukeException If the input format is incorrect or if there are empty fields.
     */
    private Deadline parseDeadline(String taskDescription) throws DukeException {
        String[] fields = taskDescription.split("/by");
        if (fields.length != 2) {
            throw new DukeException("OOPS!!! Incorrect format for deadline.");
        }
        String name = fields[0].trim();
        String dueDate = fields[1].trim();
        if (name.isEmpty() || dueDate.isEmpty()) {
            throw new DukeException("OOPS!!! Empty field for deadline detected.");
        }

        assert taskDescription != null : "TaskDescription should not be null";
        assert name != null : "Task name should not be null";
        assert dueDate != null : "Due date should not be null";

        return new Deadline(taskDescription, name, parseDate(dueDate));
    }

    /**
     * Parses the input string to create a new Event task.
     *
     * @param taskDescription The description, start date, and end date of the Event task.
     * @return A new Event task object created from the provided description.
     * @throws DukeException If the input format is incorrect or if there are empty fields.
     */
    private Event parseEvent(String taskDescription) throws DukeException {
        String[] fields = taskDescription.split("/from|/to");
        if (fields.length != 3) {
            throw new DukeException("OOPS!!! Incorrect format for event.");
        }
        String name = fields[0].trim();
        String start = fields[1].trim();
        String end = fields[2].trim();
        if (name.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new DukeException("OOPS!!! Empty field for event detected.");
        }

        assert taskDescription != null : "TaskDescription should not be null";
        assert name != null : "Task name should not be null";
        assert start != null : "Start date should not be null";
        assert end != null : "End date should not be null";

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
