package brotherman.tasks;

/**
 * Represents a task in the task list
 */
public class Task {
    /**
     * Description of the task
     */
    protected String description;

    /**
     * Whether the task is done
     */
    protected boolean isDone;

    /**
     * Constructor for Task
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the type of the task
     * @return Type of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task
     * @return Description of the task
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the description of the task
     * @return Description of the task
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Returns the description of the task
     * @return Description of the task
     */
    public String storeText() {
        return " ";
    }

    /**
     * Returns the description of the task
     * @return Description of the task
     */
    public void markedChecker(String string) {
        if (string.equals("false")) {
            return;
        } else {
            this.markAsDone();
        }
    }


    /**
     * Returns the description of the task
     * @return Description of the task
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", this.getStatusIcon(), this.description);
    }
}
