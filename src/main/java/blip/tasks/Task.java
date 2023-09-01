package blip.tasks;

import java.time.LocalDateTime;
import blip.exceptions.*;
import blip.parser.*;

/**
 * Represents a generic task.
 */
public abstract class Task {
    /**
     * Description of the task.
     */
        protected String description;

    /**
     * Boolean the represents whether task is done or not.
     */
    protected boolean isDone;

    /**
     * Constructor of Task.
     * @param description The description of the task
     * @param isDone Boolean the represents whether task is done or not
     */

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a String representation of task's status on whether it is done or not.
     * @return String representation of tasks' status
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Marks a task as done.
     */
    public void markStatus() {
        this.isDone = true;
    }

    /**
     * Unmarks a task as not done.
     */
    public void unmarkStatus() {
        this.isDone = false;
    }

    /**
     * Returns the format that task will be saved in data file.
     * @return String representation of task to save in data file
     */
    public abstract String saveToFileString();

    @Override
    public String toString() {
        return this.description;
    }

    /**
     * Loads task from data file.
     * @param lineToLoad The line from data file to load
     * @return Task object based on data file's contents
     * @throws DateTimeFormatException If date time format is incorrect in data file
     */
    public static Task loadTaskFromFile(String lineToLoad) throws DateTimeFormatException {
        String[] taskComponents = lineToLoad.split(" \\| ");
        String taskType = taskComponents[0];
        boolean isDone = taskComponents[1].equals("1");
        String taskDescription = taskComponents[2];

        if (taskType.equals("T")) {
            return new ToDo(taskDescription, isDone);
        }
        if (taskType.equals("D")) {
            LocalDateTime deadlineDateTime = DateConverter.convertToDateTime(taskComponents[3]);
            Deadline newDeadlineTask = new Deadline(taskDescription, deadlineDateTime, isDone);
            return newDeadlineTask;
        }
        if (taskType.equals("E")) {
            LocalDateTime eventFrom = DateConverter.convertToDateTime(taskComponents[3]);
            LocalDateTime eventTo = DateConverter.convertToDateTime(taskComponents[4]);
            Event newEventTask = new Event(taskDescription, eventFrom, eventTo, isDone);
            return newEventTask;
        }
        return null;
    }
}
