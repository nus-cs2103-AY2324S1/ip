package duke.task;

/**
 * The duke.task.Task class is to encapsulate the task into a single object
 * It will contain the description and whether it has been completed
 *
 * @author Zi Xiang
 * @version CS2103 AY23/24 Sem 1
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for duke.task.Task
     * @param description the task description
     */
    public Task(String description, String done) {
        this.description = description;
        this.isDone = done == "1" ? true : false;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method to check isDone status
     * @return returns a boolean value of whether the task is completed
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Method to mark task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Method to mark task as undone
     */
    public void unmark() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String fileRepresentation() {
        // To override
        return "";
    }
}
