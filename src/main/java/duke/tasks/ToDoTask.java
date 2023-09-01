package duke.tasks;

import duke.tasks.Task;

/**
 * Encapsulates a task without any time/date attached.
 * Inherits from duke.tasks.Task class.
 * @author Wu Jingya
 */
public class ToDoTask extends Task {

    /**
     * Creates a ToDo duke.tasks.Task with the specified description
     * @param description The task's description
     */
    public ToDoTask(String description) {
        super(description);
    }

    public ToDoTask(String description, Boolean done) {
        super(description, done);
    }

    /**
     * Returns the string representation of the task
     * @return The string representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toData() {
        return "T|" + super.toData();
    }

    //For testing purposes only
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ToDoTask) {
            return super.equals(((ToDoTask) obj));
        }
        return false;
    }
}
