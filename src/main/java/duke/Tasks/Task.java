package duke.Tasks;

public class Task {
    /**
     * Taken from the Partial Solution given on https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html
     * A class of duke.Tasks to create tasks that need to be in list of tasks.
     */
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String addDataFormat() {
        if (this.isDone) {
            return "| 1 | " + this.description;
        } else {
            return "| 0 | " + this.description;
        }
    }
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }
}