package qi.task;

/**
 * Represents a task categorized as todo
 * by users.
 */
public class Todo extends Task {

    /**
     * Takes in the description of the task.
     *
     * @param task String description of the task.
     */
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[T][X] " + this.task;
        }
        return "[T][ ] " + this.task;
    }
}

