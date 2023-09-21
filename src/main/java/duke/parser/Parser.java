package duke.parser;

import duke.command.*;
import duke.data.exception.CCException;
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
     * @throws CCException If an error occurs during parsing, such as an empty input.
     */
    public Command parseInput(String input) throws CCException {
        String action = getActionFromInput(input);
        String description = getDescriptionFromInput(input, action);

        Command command;

        switch (action) {
        case "todo":
        case "deadline":
        case "event":
            command = new AddTaskCommand(parseTask(action, description));
            break;
        case "list":
            command = new PrintListCommand();
            break;
        case "mark":
            command = new MarkTaskCommand(description);
            break;
        case "unmark":
            command = new UnmarkTaskCommand(description);
            break;
        case "delete":
            command = new DeleteTaskCommand(description);
            break;
        case "find":
            command = new FindTaskCommand(description);
            break;
        case "completed":
            command = new CountCompletedTasksCommand();
            break;
        case "uncompleted":
            command = new CountUncompletedTasksCommand();
            break;
        case "count":
            command = new CountTaskTypeCommand();
            break;
        default:
            throw new CCException("OOPS!!! I'm sorry, but I don't know what that means :<");
        }

        assert action != null : "Action should not be null";
        assert description != null : "Description should not be null";

        return command;
    }

    private String getActionFromInput(String input) throws CCException {
        if (input.trim().isEmpty()) {
            throw new CCException("Empty input detected.");
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
     * @throws CCException If an error occurs during parsing.
     */
    public Task parseTaskFromFile(String fileLine) throws CCException {
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
        default:
            throw new CCException("Invalid task type.");
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

        assert taskDescription != null : "TaskDescription should not be null";

        return new ToDo(taskDescription);
    }

    /**
     * Parses the input string to create a new Deadline task.
     *
     * @param taskDescription The description and due date of the Deadline task.
     * @return A new Deadline task object created from the provided description.
     * @throws CCException If the input format is incorrect or if there are empty fields.
     */
    private Deadline parseDeadline(String taskDescription) throws CCException {
        String[] fields = taskDescription.split("/by");
        if (fields.length != 2) {
            throw new CCException("OOPS!!! Incorrect format for deadline.");
        }
        String name = fields[0].trim();
        String dueDate = fields[1].trim();
        if (name.isEmpty() || dueDate.isEmpty()) {
            throw new CCException("OOPS!!! Empty field for deadline detected.");
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
     * @throws CCException If the input format is incorrect or if there are empty fields.
     */
    private Event parseEvent(String taskDescription) throws CCException {
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
