package duke.task;

/**
 * Encapsulates the logic of an todo task
 */
public class TodoTask extends Task {

    /**
     * Constructs a new TodoTask instance
     * Stored as TODO{task description}{1 if marked else 0}
     *
     * @param task - the description of the task created
     */
    public TodoTask(String task) {
        super(task);
    }

    /**
     * Constructs a new TodoTask instance from storage
     * Stored as TODO{task description}{1 if marked else 0}
     *
     * @param task       - the description of the task created
     * @param isComplete - if completed
     */
    public TodoTask(String task, boolean isComplete) {
        super(task);
        if (isComplete) {
            this.toggleComplete();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the stored form of this tasks
     * Stored as TODO{task description}{1 if marked else 0}
     *
     * @return TODO{task description}{1 if marked else 0}
     */
    @Override
    public String getStored() {
        return String.join(Task.SEP, new String[] { "TODO", this.getTask(), this.isComplete() ? "1" : "0" });
    }

    /**
     * Returns the stored form of the task
     *
     * @param days - the range of days of task to be reminded
     * @return true if this task need to be reminded
     */
    @Override
    public boolean isRemind(int days) {
        return false;
    }
}
