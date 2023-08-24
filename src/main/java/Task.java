/**
 * CS2103T IP
 * AY 23/24 Semester 1
 *
 * <p> An abstract Task class</p>
 *
 * @author Koo Yu Cong
 * @version CS2103T AY 23/24 Sem 1
 */
public abstract class Task {
    private String description;
    private boolean done;

    /**
     * A constuctor that constructs a Task with a task name
     * @param taskName The name of the constructed task
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks this task as done or not done.
     * @param done Whether the task is done or not
     */
    public void markTask(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        char marked = '\u2717';
        return "[" + (done ? marked : " ") + "] " + this.description;
    }
}
