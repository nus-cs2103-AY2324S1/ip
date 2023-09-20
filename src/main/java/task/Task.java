package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The task.Task Class is an abstract class that serves as a base class for various types of task the user can create.
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Constructs a new task.Task with the specified name. By default,
     * the task is marked as not done.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructs a new task.Task with the specified name.
     * Task constructed can be marked as completed.
     *
     * @param name The name of the task.
     * @param isDone If task is completed.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Returns a string representing if the task is marked as done or not.
     *
     * @return The string message if task is done.
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as done.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns the string description of the current task. Description includes if task is done and the task name.
     */
    @Override
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), name);
    }

    /**
     * Returns string of task to store in file. Description includes
     * task type, date, if task is done and the task name.
     */
    public String fileString() {
        String done = isDone ? "1" : "0";
        String fileLine = String.format(" | %s | %s", done, name);

        return fileLine;
    }

    /**
     * Returns true if task is due to be completed. There will be reminder for a task if task
     * is not completed and the task date to be completed by is not passed the current date.
     */
    public abstract boolean needReminder();

    /**
     * Parses the string date field of a task to a LocalDateTime object.
     *
     * @param date The input task date.
     * @return The LocalDateTime corresponding to the string input.
     */
    protected static LocalDateTime parseTaskDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h.mma", Locale.ENGLISH);
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

        return dateTime;
    }
}
