package blip.tasks;

import java.time.LocalDateTime;
import blip.exceptions.DateTimeFormatException;
import blip.parser.DateConverter;
import blip.parser.BlipParser;
import blip.priority.Priority;


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
     * Priority to indicate how important a task is.
     */
    protected Priority priority;


    /**
     * Creates an instance of Task.
     * @param description The description of the task
     * @param isDone Boolean the represents whether task is done or not
     */

    public Task(String description, boolean isDone, Priority priority) {
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
    }

    /**
     * Returns a String representation of task's status on whether it is done or not.
     * @return String representation of tasks' status
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getPriority() {
        return "[" + this.priority + "] ";
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
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
        String priorityString = taskComponents[2];
        Priority priority = BlipParser.convertToPriority(priorityString);
        String taskDescription = taskComponents[3];

        if (taskType.equals("T")) {
            return new ToDo(taskDescription, isDone, priority);
        }
        if (taskType.equals("D")) {
            LocalDateTime deadlineDateTime = DateConverter.convertToDateTime(taskComponents[4]);
            Deadline newDeadlineTask = new Deadline(taskDescription, deadlineDateTime, isDone, priority);
            return newDeadlineTask;
        }
        if (taskType.equals("E")) {
            LocalDateTime eventFrom = DateConverter.convertToDateTime(taskComponents[4]);
            LocalDateTime eventTo = DateConverter.convertToDateTime(taskComponents[5]);
            Event newEventTask = new Event(taskDescription, eventFrom, eventTo, isDone, priority);
            return newEventTask;
        }
        return null;
    }
}
