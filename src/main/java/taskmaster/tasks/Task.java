package taskmaster.tasks;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, String marked) {
        this.description = description;
        if (marked == "marked") {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    /**
     * Returns description of task.
     * @return String of description of task.
     */
    public String getDescription() {
        return this.description;
    }
}
