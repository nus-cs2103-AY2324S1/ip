package chatbot;

/**
  * Stores the task details.
  */
public class Task {
    /** Basic task description */
    protected String description;
    /** Determine whether the task is completed */
    protected boolean isDone;

    /**
     * Constructor for Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns [ ] for not marked, [X] for marked.
     *
     * @return String representation of whether it is marked.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Returns the string representation of its type.
     *
     * @return String representation of its type.
     */
    public String getTypeIcon() {
        return "[ ]";
    }

    /**
     * Returns any extra information required by the task.
     *
     * @return String representation of the extra information.
     */
    public String getExtras() {
        return "";
    }
    
    /**
     * Marks the task as completed
     */
    public void markTaskDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed
     */
    public void markTaskNotDone() {
        isDone = false;
    }
}