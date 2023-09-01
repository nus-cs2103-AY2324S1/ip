package duck.task;

/**
 * Contatins the information of the Task.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs the task object.
     * @param description Contains the description of the event.
     */
    public Task(String description) {
        this.description = description.strip();
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String box() {
        return "[" + getStatusIcon() + "] ";
    }

    public void markAsDone() {
        isDone = true;
    }


    public void markUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[ ]" + this.box() + this.description;
    }

    public String type() {
        return "";
    }

    public String getBy() {
        return "";
    }

}

