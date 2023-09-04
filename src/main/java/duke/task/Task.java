package duke.task;

import duke.InvalidCommandException;
import duke.InvalidTaskCreationException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * The `Task` class represents a task in the Duke application. It serves as the base class for different types of tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new `Task` with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Date and Time formats
    public static final String dateFormat = "yyyy-MM-dd";
    public static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DTformatter = DateTimeFormatter.ofPattern(dateTimeFormat);
    public static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm, dd MMMM yyyy");

    /**
     * Parses user input and creates a specific type of task (Todo, Deadline, or Event).
     *
     * @param userInput The user input to parse.
     * @return An instance of a specific task type (Todo, Deadline, or Event).
     * @throws InvalidCommandException      if the user input does not match any valid command.
     * @throws InvalidTaskCreationException if there is an issue creating the task from the input.
     * @throws DateTimeParseException       if there is an issue parsing date and time.
     */
    public static Task taskCon(String userInput) throws InvalidCommandException, InvalidTaskCreationException, DateTimeParseException {
        if (userInput.startsWith("todo")) {
            return ToDo.ToDoCon(userInput.substring(5));
        } else if (userInput.startsWith("deadline")) {

            String[] splitInput = userInput.split("/by");
            String taskDescription = splitInput[0].substring(9).trim();
            String deadline = splitInput[1].trim();

            return Deadline.DeadlineCon(taskDescription, deadline);

        } else if (userInput.startsWith("event")) {

            String[] splitInput = userInput.split("/from");
            String taskDescription = splitInput[0].substring(6).trim();
            String[] eventDetails = splitInput[1].split("/to");
            String eventStartTime = eventDetails[0].trim();
            String eventEndTime = eventDetails[1].trim();

            return Event.EventCon(taskDescription, eventStartTime, eventEndTime);
        } else {
            throw new InvalidCommandException("Invalid command to add task!");
        }
    }

    /**
     * Gets the urgency date and time for the task.
     *
     * @return The urgency date and time.
     */
    public LocalDateTime getUrgencyDate() {
        return LocalDateTime.now();
    }

    /**
     * Gets the status icon for the task (either "X" for done or " " for not done).
     *
     * @return The status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Converts the task to a formatted string representation for display.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + this.description;
    }
}
