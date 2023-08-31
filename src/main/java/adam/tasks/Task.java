package adam.tasks;

import java.io.Serializable;

/**
 * This class holds information about the task such as completion and description.
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
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns an X when a task is complete and empty space when it is not.
     *
     * @return String for its completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
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

    /**
     * Returns boolean depending on whether or not the param exists in the description.
     *
     * @param item String that is being checked.
     * @return Boolean.
     */
    public boolean search(String item) {
        boolean isIdentical = true;
        String[] tokens = item.split(" ");
        String[] descriptions = description.split(" ");
        for (String word : tokens) {
            for (String line : descriptions) {
                if (!word.equals(line)) {
                    isIdentical = false;
                }
            }
        }
        return isIdentical;
    }
}
