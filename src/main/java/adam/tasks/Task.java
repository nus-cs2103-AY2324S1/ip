package adam.tasks;

import java.io.Serializable;

/**
 * This class is used as the superclass to all three types o adam.tasks that is used in the chatbot Adam.Adam
 * This class holds information such as the description of the task and whether or not someone has completed it
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes the deacription and the completion of the task.
     *
     * @param description Describe the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Returns the strung form of the Tasks.
     *
     * @return The string form and information about this task.
     */
    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns an X when a task is complete and empty space when it is not.
     *
     * @return String for its completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Completes this task.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println(this.toString());
    }

    /**
     * Unmarks the task as incomplete.
     */
    public void unmarkAsDone(){
        this.isDone = false;
        System.out.println(this.toString());
    }
}
