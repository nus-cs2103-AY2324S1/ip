package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DeadlineException;
import duke.exception.DukeException;
import duke.exception.EventException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a parser that parses the user input.
 */
public class Parser {
    private static final DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param userInput The user input.
     * @return The corresponding command.
     * @throws DukeException If the user input is invalid.
     */
    public static Command parse(String userInput) throws DukeException {
        String commandType = getCommandType(userInput);

        switch (commandType) {
        case "bye":
        case "list":
            return new Command(commandType);

        case "mark":
        case "delete":
        case "unmark":
            return new Command(commandType, getTaskIndex(userInput)); // taskIndex is -1 if invalid

        case "todo":
        case "find":
            return new Command(commandType, getDescription(userInput));
        case "deadline":
            return new Command(commandType, getDeadlineDescription(userInput), getDeadlineDate(userInput));
        case "event":
            LocalDate[] eventDates = getEventDates(userInput);
            LocalDate eventFromDate = eventDates[0];
            LocalDate eventToDate = eventDates[1];

            return new Command(commandType, getEventDescription(userInput), eventFromDate, eventToDate);

        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the data string from the storage file and returns the corresponding task.
     * example of data string:
     * T | 1 | read book
     * D | 0 | return book | June 6th
     * E | 0 | project meeting | Aug 6th 2-4pm
     * File corruption is not handled.
     *
     * @param taskData The data string from the storage file.
     * @return The completion status of the task.
     */
    public static Task parseFileString(String taskData) {
        String[] parts = taskData.split(" \\| ");
        String type = parts[0].trim();
        if (parts.length < 3) {
            return null;
        }
        String isDone = parts[1].trim();
        String description = parts[2].trim();

        Task task = null;

        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            String by = parts[3].trim();
            task = new Deadline(description, by);
            break;
        case "E":
            String from = parts[3].trim();
            String to = parts[4].trim();
            task = new Event(description, from, to);
            break;
        default:
            break;
        }

        if (isDone.equals("1")) {
            assert task != null;
            task.markAsDone();
        }

        return task;
    }

    /**
     * reads the task and returns the corresponding data string to be written to the storage file.
     *
     * @param task The task to be read.
     * @return String to be written to the storage file.
     */
    public static String readTaskToFile(Task task) {
        StringBuilder data = new StringBuilder();

        if (task instanceof Todo) {
            data.append("T");
            data.append(" | ").append(task.isDone() ? "1" : "0");
            data.append(" | ").append(task.getDescription());
        } else if (task instanceof Deadline) {
            data.append("D");
            data.append(" | ").append(task.isDone() ? "1" : "0");
            data.append(" | ").append(task.getDescription());
            data.append(" | ").append(((Deadline) task).getBy());
        } else if (task instanceof Event) {
            data.append("E");
            data.append(" | ").append(task.isDone() ? "1" : "0");
            data.append(" | ").append(task.getDescription());
            data.append(" | ").append(((Event) task).getFrom()).append(" | ").append(((Event) task).getTo());
        }

        return data.toString();
    }

    private static String getCommandType(String userInput) {
        String[] parts = userInput.split(" ");
        return parts.length > 0 ? parts[0] : "";
    }

    public static String getDescription(String userInput) {
        String[] parts = userInput.split(" ", 2);
        return parts.length > 1 ? parts[1] : "";
    }

    public static String getDeadlineDescription(String userInput) {
        String[] parts = userInput.split(" ", 2);
        return parts.length > 1 ? parts[1].split("/by", 2)[0].trim() : "";
    }

    public static String getEventDescription(String userInput) {
        String[] parts = userInput.split(" ", 2);
        return parts.length > 1 ? parts[1].split("/from", 2)[0].trim() : "";
    }

    /**
     * Returns the task index from the user input.
     *
     * @param userInput The user input.
     * @return The task index.
     * @throws DukeException If the task index is not numeric.
     */
    public static int getTaskIndex(String userInput) throws DukeException {
        String[] parts = userInput.split(" ");
        if (parts.length == 2) {
            try {
                return Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                throw new DukeException("Error, please enter a number.");
            }
        }
        throw new DukeException("Error, please enter a number.");
    }

    /**
     * Returns the deadline date from the user input.
     * example of deadline date: 2020-02-02
     *
     * @param userInput The user input.
     * @return The deadline date.
     * @throws DeadlineException If the deadline date is empty or invalid.
     */
    private static LocalDate getDeadlineDate(String userInput) throws DeadlineException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2) {
            throw new DeadlineException("detail: Description or deadline date is empty");
        }

        String description = parts[1];

        // Check if /by exists in the description
        if (!description.contains("/by")) {
            throw new DeadlineException("detail: Description does not contain /by");
        }

        String[] desArray = description.split("/by", 2);
        String taskDescription = desArray[0].trim();
        String deadlineDate = desArray[1].trim();

        if (taskDescription.isEmpty() || deadlineDate.isEmpty()) {
            throw new DeadlineException("detail: Description or deadline date is empty");
        }

        try {
            // Validate date format and return LocalDate object
            return LocalDate.parse(deadlineDate, dataFormatter);
        } catch (DateTimeParseException e) {
            throw new DeadlineException("detail: Invalid date format. Please use yyyy-MM-dd");
        }
    }

    /**
     * Returns the event dates from the user input.
     * example of event dates: 2020-02-02 2020-02-03
     *
     * @param userInput The user input.
     * @return The event dates.
     * @throws EventException If the event dates are empty or invalid.
     */
    private static LocalDate[] getEventDates(String userInput) throws EventException {
        String[] desArray = getDesArray(userInput);
        String taskDescription = desArray[0].trim();

        // Split the timing description further using /to
        String[] timingArr = desArray[1].split("/to", 2);
        String eventFrom = timingArr[0].trim();
        String eventTo = timingArr[1].trim();

        if (taskDescription.isEmpty() || eventFrom.isEmpty() || eventTo.isEmpty()) {
            throw new EventException("details: Description or event dates are empty");
        }

        try {
            LocalDate eventFromDate = LocalDate.parse(eventFrom, dataFormatter);
            LocalDate eventToDate = LocalDate.parse(eventTo, dataFormatter);
            return new LocalDate[]{eventFromDate, eventToDate};
        } catch (DateTimeParseException e) {
            throw new EventException("details: Invalid date format. Please use yyyy-MM-dd");
        }
    }

    /**
     * Returns the description from the user input.
     * example of description: project meeting /from 2020-02-02 /to 2020-02-03
     *
     * @param userInput The user input.
     * @return The description.
     * @throws EventException If the description is empty or invalid.
     */
    private static String[] getDesArray(String userInput) throws EventException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2) {
            throw new EventException("details: Description or event dates are empty");
        }
        String description = parts[1];

        // Check if both /from and /to exist in the description
        if (!description.contains("/from") || !description.contains("/to")) {
            throw new EventException("details: Description does not contain /from or /to");
        }

        String[] desArray = description.split("/from", 2);
        return desArray;
    }
}
