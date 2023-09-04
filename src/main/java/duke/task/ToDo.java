package duke.task;

/**
 * To-Do class inherits from Task class.
 * A To-Do object encapsulates the task name and status.
 *
 * @author ruo-x
 */
public class ToDo extends Task {
    /**
     * Constructor of a To-Do Object.
     *
     * @param isDone Status of task.
     * @param taskName Name of task.
     */
    public ToDo(boolean isDone, String taskName) {
        super(isDone, taskName);
    }

    /**
     * Returns a string format of a To-Do object.
     *
     * @return String format of a To-Do object.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[T]" + "[X] " + this.taskName;
        } else {
            return "[T]" + "[ ] " + this.taskName;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toStoreString() {
        if (this.isDone) {
            return "T/@/1/@/" + this.taskName;
        } else {
            return  "T/@/0/@/" + this.taskName;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toUpdateString(int i) {
        return "T/@/" + i + "/@/" + this.taskName;
    }
}
