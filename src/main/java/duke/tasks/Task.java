package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exceptions.BackwardsTimeException;
import duke.exceptions.NoDescriptionException;
import duke.exceptions.UnknownTimeException;

/**
 * Represents a task in the chat bot.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Constructs a task with the given description and done status.
     *
     * @param description The description of the task.
     * @param isDone      The done status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return The string representation of the status icon.
     */
    public String getStatus() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("%s %s", getStatus(), description);
    }

    /**
     * Formats the task for storage in a data file.
     *
     * @return The formatted string for storage.
     */
    public String formatForStorage() {
        return String.format("%s | %s", isDone ? "1" : "0", description);
    }

    /**
     * Checks if the task is within the given date range.
     *
     * @param date The date to check against.
     * @return True if the task is within the date range, false otherwise.
     */
    public boolean isWithinDateRange(LocalDateTime date) {
        assert date != null;
        return false;
    }

    /**
     * Checks if the task's description contains the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return True if the description contains the keyword, false otherwise.
     */
    public boolean isRelatedContent(String keyword) {
        assert keyword != null;
        return description.contains(keyword);
    }

    /**
     * Creates a ToDo task from the given command.
     *
     * @param fullCommand The full command for creating the ToDo task.
     * @return A new ToDo task.
     * @throws NoDescriptionException If no description is provided.
     */
    public static Task createToDo(String fullCommand) throws NoDescriptionException {
        //Removes the command type from the entire command
        String desc = fullCommand.replaceAll("^\\s*todo\\s*", "");
        if (desc.equals("")) {
            throw new NoDescriptionException("todo");
        }

        return new ToDo(desc);
    }

    /**
     * Creates a Deadline task from the given command.
     *
     * @param fullCommand The full command for creating the Deadline task.
     * @param formatter   The DateTimeFormatter for parsing the deadline time.
     * @return A new Deadline task.
     * @throws NoDescriptionException If no description is provided.
     * @throws UnknownTimeException   If the time format is unknown.
     */
    public static Task createDeadline(String fullCommand, DateTimeFormatter formatter)
            throws NoDescriptionException, UnknownTimeException {
        //Removes the command type from the entire command
        String deadlineTime = fullCommand.replaceAll("^\\s*deadline\\s*", "");

        // Separates into the description and the deadline
        String[] strings = deadlineTime.split(" /by ");

        if (deadlineTime.equals("")) {
            throw new NoDescriptionException("deadline");
        }
        if (strings.length == 1) {
            throw new UnknownTimeException(strings[0]);
        }

        return new Deadline(strings[0], LocalDateTime.parse(strings[1], formatter));
    }

    /**
     * Creates an Event task from the given command.
     *
     * @param fullCommand The full command for creating the Event task.
     * @param formatter   The DateTimeFormatter for parsing the event times.
     * @return A new Event task.
     * @throws NoDescriptionException   If no description is provided.
     * @throws UnknownTimeException     If the time format is unknown.
     * @throws BackwardsTimeException   If the event times are in the wrong order.
     */
    public static Task createEvent(String fullCommand, DateTimeFormatter formatter)
            throws NoDescriptionException, UnknownTimeException, BackwardsTimeException {
        //Removes the command type from the entire command
        String content = fullCommand.replaceAll("^\\s*event\\s*", "");
        if (content.equals("")) {
            throw new NoDescriptionException("event");
        }

        // Separates the command into the description and the times
        String[] descTime = content.split(" /from ");

        // Separates into the start time and end time
        String[] times = descTime[1].split(" /to ");

        if (times.length == 1) {
            throw new UnknownTimeException(descTime[0]);
        }

        assert times.length == 2 : "Incorrect timing format";

        LocalDateTime start = LocalDateTime.parse(times[0], formatter);
        LocalDateTime end = LocalDateTime.parse(times[1], formatter);

        if (start.isAfter(end)) {
            throw new BackwardsTimeException();
        }

        return new Event(descTime[0], start, end);
    }
}
