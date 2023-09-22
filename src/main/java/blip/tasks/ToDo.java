package blip.tasks;

import blip.priority.Priority;

/**
 * Represents To Do task.
 */
public class ToDo extends Task{
    /**
     * Creates an instance of To Do task, sets priority to desired priority.
     * @param description The description of the to do task
     * @param isDone Boolean that represents whether task is done
     */
    public ToDo(String description, boolean isDone, Priority priority) {
        super(description, isDone, priority);
    }

    /**
     * Saves the to do task to data file in this specified format.
     * @return String representation of deadline task to save in data file
     */
    @Override
    public String saveToFileString() {
        return "T " + (super.isDone ? "| 1 | " : "| 0 | ")
                + (super.priority + " | ")
                + super.toString();
    }

    /**
     * Returns a String representation of the to do task.
     * @return String representation of to do task
     */
    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + super.getPriority() + super.toString();
    }
}
