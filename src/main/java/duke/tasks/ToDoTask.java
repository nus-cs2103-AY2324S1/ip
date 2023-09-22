package duke.tasks;

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
     * @param isDone Whether the task is completed.
     */
    public ToDoTask(String description, Boolean isDone) {
        super(description, isDone);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareDate(Task otherTask) {
        if (otherTask instanceof DeadlineTask) {
            return 1;
        } else if (otherTask instanceof EventTask) {
            return 1;
        } else if (otherTask instanceof ToDoTask) {
            return 0;
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareType(Task otherTask) {
        if (otherTask instanceof DeadlineTask) {
            return -1;
        } else if (otherTask instanceof EventTask) {
            return -1;
        } else if (otherTask instanceof ToDoTask) {
            return 0;
        }
        return 0;
    }
}
