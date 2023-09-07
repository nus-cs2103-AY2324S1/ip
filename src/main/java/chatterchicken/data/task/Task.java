package chatterchicken.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The abstract Task class represents a generic task with a task description, name, and completion status.
 * Subclasses such as ToDo, Deadline, and Event provide specific task types.
 */
public abstract class Task {

    private final String taskDescription;
    private final String name;
    private boolean isDone;

    /**
     * Constructs a new Task with the provided task description and name.
     *
     * @param taskDescription The description of the task.
     * @param name The name or title of the task.
     */
    public Task(String taskDescription, String name) {
        this.taskDescription = taskDescription;
        this.name = name;
        this.isDone = false;
    }

    protected String getName() {
        return this.name;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Gets a formatted input string for the task.
     *
     * @return The formatted input string representing the task.
     */
    public abstract String getInput();

    /**
     * Gets a formatted string for printing the task.
     *
     * @return The formatted string for printing the task.
     */
    public abstract String getTaskForPrinting();

    /**
     * Gets a formatted string for saving the task, including its completion status.
     *
     * @return The formatted string for saving the task.
     */
    public String getTaskForSaving() {
        String done = "";
        if (isDone) {
            done = "X";
        } else {
            done = " ";
        }
        return done + getInput(); //append completion status character to input
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone True if the task is completed; false otherwise.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Checks the completion status of the task and returns 'X' if completed, or ' ' if not completed.
     *
     * @return 'X' if the task is completed; ' ' if not completed.
     */
    protected String checkDone() {
        return isDone ? "X" : " ";
    }

    /**
     * Formats a given LocalDate object into a string using the "dd/MM/yyyy" pattern.
     *
     * @param date The LocalDate object to be formatted.
     * @return The formatted date string.
     */
    public String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }
}
