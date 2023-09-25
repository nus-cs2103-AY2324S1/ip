package tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import enums.TaskType;
import woof.Woof;

/**
 * The `Task` class represents a task in the Woof application.
 */
public abstract class Task implements Comparable<Task> {
    /**
     * The first tab stop position, typically at column 5.
     * Adjust this constant to change the position of the first tab stop.
     */
    static final int TAB_STOP_ONE = 5;

    /**
     * The second tab stop position, typically at column 12.
     * Adjust this constant to change the position of the second tab stop.
     */
    static final int TAB_STOP_TWO = 12;

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * A flag indicating whether the task is marked as done.
     */
    private boolean isDone;

    /**
     * Constructs a `Task` with the given description and initializes it as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null : "description cannot be null";

        this.description = Woof.wrapText(
            description,
            getTabStopTwo(),
            Woof.getChatWidth() - TAB_STOP_TWO);
        this.isDone = false;
    }

    /**
     * Constructs a `Task` with the given description and initializes it as not done.
     *
     * @param description The description of the task.
     * @param isDone      The optional is done to mark a task as per is done.
     */
    public Task(String description, Boolean isDone) {
        assert description != null : "description cannot be null";
        assert isDone != null : "is done cannot be null";

        this.description = Woof.wrapText(
            description,
            getTabStopTwo(),
            Woof.getChatWidth() - TAB_STOP_TWO);
        this.isDone = isDone;
    }

    /**
     * Formats a LocalDate into a user-friendly date string for presentation.
     *
     * @param localDate The LocalDate to format.
     * @return A formatted date string.
     */
    public static String parseDateTimeOut(LocalDate localDate) {
        return Woof.parseDateTimeOut(localDate);
    }

    /**
     * Gets the status icon representing the task's completion status.
     *
     * @return The status icon ("[X]" for done, "[ ]" for not done).
     */
    private String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Gets a tab stop to the first level for indenting a task to the same level as in a list.
     *
     * @return The tab space "    ".
     */
    protected String getTabStopOne() {
        return " ".repeat(TAB_STOP_ONE);
    }

    /**
     * Gets a tab stop to the second level for indenting to the description of a task.
     *
     * @return The tab space "            ".
     */
    protected String getTabStopTwo() {
        return " ".repeat(TAB_STOP_TWO);
    }

    /**
     * Generates a string representation of the `Task`.
     *
     * @return A string representation of the task, including its status icon and description.
     */
    public String toString() {
        return String.format("%s %s\n", this.getStatusIcon(), this.description);
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return `true` if the task is done, `false` otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Checks if the task description contains a specified keyword.
     *
     * @param keyword The keyword to search for within the task description.
     * @return `true` if the task description contains the keyword, `false` otherwise.
     */
    public boolean hasKeyWord(String keyword) {
        return this.description.contains(keyword);
    }

    public abstract TaskType getTaskType();

    public abstract long getDateTimeLong();

    /**
     * Compares this task to another task for ordering based on specific criteria.
     * The comparison is primarily based on the following criteria, in order of priority:
     * 1. Task completion status (done or not done).
     * 2. Task type (Deadline, Event, Todo).
     * 3. Date and time associated with the task (if applicable).
     * 4. Task description (in case of equal status, type, and date/time).
     *
     * @param otherTask The task to compare to.
     * @return A negative integer, zero, or a positive integer as this task is less than,
     *         equal to, or greater than the specified task.
     */
    @Override
    public int compareTo(Task otherTask) {
        final ArrayList<TaskType> taskPriority = new ArrayList<>(Arrays.asList(
            TaskType.DEADLINE,
            TaskType.EVENT,
            TaskType.TODO)
        );

        int doneCompare = Boolean.compare(
            this.isDone(),
            otherTask.isDone());
        if (doneCompare != 0) {
            return doneCompare;
        }

        int priorityCompare = Integer.compare(
            taskPriority.indexOf(this.getTaskType()),
            taskPriority.indexOf(otherTask.getTaskType())
        );
        if (priorityCompare != 0) {
            return priorityCompare;
        }

        int dateTimeCompare = Long.compare(
            this.getDateTimeLong(),
            otherTask.getDateTimeLong());
        if (dateTimeCompare != 0) {
            return dateTimeCompare;
        }

        return this.description.compareTo(otherTask.description);
    }

    /**
     * Checks if this `Task` is equal to another object.
     *
     * @param o The object to compare to.
     * @return `true` if the objects are equal, `false` otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task otherTask = (Task) o;
        return this.isDone == otherTask.isDone && this.description.equals(otherTask.description);
    }

    /**
     * Generates a hash code for this `Task`.
     *
     * @return A hash code for the task, including its description and completion status.
     */
    @Override
    public int hashCode() {
        return Objects.hash(description, isDone);
    }
}
