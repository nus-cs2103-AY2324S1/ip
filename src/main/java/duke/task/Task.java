package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import duke.InvalidCommandException;
import duke.InvalidTaskCreationException;

/**
 * The `Task` class represents a task in the Duke application. It serves as the base class for different types of tasks.
 */
public class Task {
    // Date and Time formats
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    public static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("HH:mm, dd MMMM yyyy");
    public static final int BEGIN_INDEX_TODO = 5;
    public static final int BEGIN_INDEX_DEADLINE = 9;
    public static final int BEGIN_INDEX_EVENT = 6;
    protected String description;
    protected boolean isDone;

    protected ArrayList<String> tagsList;

    protected Task parentTask;

    /**
     * Constructs a new `Task` with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tagsList = new ArrayList<>();
        this.parentTask = null;
    }

    /**
     * Parses user input and creates a specific type of task (Todo, Deadline, or Event).
     *
     * @param userInput The user input to parse.
     * @return An instance of a specific task type (Todo, Deadline, or Event).
     * @throws InvalidCommandException      if the user input does not match any valid command.
     * @throws InvalidTaskCreationException if there is an issue creating the task from the input.
     * @throws DateTimeParseException       if there is an issue parsing date and time.
     */

    public static Task taskCon(String userInput) throws InvalidCommandException, InvalidTaskCreationException,
            DateTimeParseException {
        if (userInput.startsWith("todo")) {
            return ToDo.toDoCon(userInput.substring(BEGIN_INDEX_TODO));
        } else if (userInput.startsWith("deadline")) {

            String[] splitInput = userInput.split("/by");
            String taskDescription = splitInput[0].substring(BEGIN_INDEX_DEADLINE).trim();
            String deadline = splitInput[1].trim();

            return Deadline.deadlineCon(taskDescription, deadline);

        } else if (userInput.startsWith("event")) {

            String[] splitInput = userInput.split("/from");
            String taskDescription = splitInput[0].substring(BEGIN_INDEX_EVENT).trim();

            String[] eventDetails = splitInput[1].split("/to");
            String eventStartTime = eventDetails[0].trim();
            String eventEndTime = eventDetails[1].trim();

            return Event.eventCon(taskDescription, eventStartTime, eventEndTime);
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
    public String getDescription() {
        return this.description;
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

    public void addTags(String... tags) {
        tagsList.addAll(Arrays.asList(tags));
    }

    public String printTags() {
        StringBuilder result = new StringBuilder();
        for (String tag : this.tagsList) {
            result.append("#").append(tag).append(" ");
        }
        return result.toString().trim(); // Remove trailing space
    }

    public String printDoAfter() {
        if (this.parentTask == null) {
            return "DoAfter : " + "NONE";
        } else {
            return "DoAfter : " + this.parentTask.description;
        }
    }

    public void setParentTask(Task parent) {
        this.parentTask = parent;
    }

    /**
     * Converts the task to a formatted string representation for display.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + this.description + " " + this.printTags() + " | " + this.printDoAfter() + " | ";
    }

    /**
     * Gets the parent task for the task (either "X" or null for no parent).
     *
     * @return The parent task.
     */
    public Task getParentTask() {
        return this.parentTask;
    }
}
