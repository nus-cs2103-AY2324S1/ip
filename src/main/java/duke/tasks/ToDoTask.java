package duke.tasks;

import duke.tasks.Task;

/**
 * Encapsulates a task without any time/date attached.
 * Inherits from Task class.
 *
 * @author Wu Jingya
 */
public class ToDoTask extends Task {

    /**
     * Constructs a ToDoTask with the specified description.
     *
     * @param description The task's description.
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Constructs a ToDoTask with the specified description and completion status.
     *
     * @param description The task's description.
     * @param done Whether the task is completed.
     */
    public ToDoTask(String description, Boolean done) {
        super(description, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toData() {
        return "T|" + super.toData();
    }

    // for testing purposes only
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
