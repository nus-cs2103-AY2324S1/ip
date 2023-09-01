package duke.tasks;

/**
 * An abstract class encapsulating a task in the task list.
 * @author Wu Jingya
 */
public abstract class Task {
    private String description;
    private boolean done;

    /**
     * Creates a duke.tasks.Task with the specified description
     * @param description The task's description
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public Task(String description, Boolean done) {
        this.description = description;
        this.done = done;
    }

    /**
     * Marks or unmarks the task as done
     * @param done Whether the task is done
     */
    public void markTaskCompleted(boolean done) {
        this.done = done;
    }

    /**
     * Returns the string representation of the task
     * @return The string representation of the task
     */
    @Override
    public String toString() {
        String checkbox = "";
        if (this.done) {
            checkbox = "[X]";
        } else {
            checkbox = "[ ]";
        }
        return checkbox + " " + this.description;
    }

    public String toData() {
        if (done) {
            return "1|" + this.description;
        } else {
            return "0|" + this.description;
        }
    }

    //For testing purposes only
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Task) {
            return ((Task) obj).description.equals(this.description) && ((Task) obj).done == this.done;
        }
        return false;
    }
}
