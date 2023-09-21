package qi.task;

/**
 * Represents the task input by users.
 */
public class Task {
    protected String task;
    protected boolean isDone;

    /**
     * Receives task description in form of a string.
     *
     * @param task String representation of the task.
     */
    protected Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Marks the status of a task as done or undone.
     *
     * @param isDone Boolean value indicating that a task is done
     *               if true; otherwise, the task is undone.
     */
    public void mark(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Checks whether the task description contains
     * a keyword or not.
     *
     * @param keyWord String representing the keyword
     *                being examined.
     * @return Boolean value indicating whether the
     *         task description has the input keyword
     *         or not.
     */
    public boolean isMatching(String keyWord) {
        return this.task.contains(keyWord);
    }
}
