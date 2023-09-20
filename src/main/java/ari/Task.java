package ari;

/**
 * A task class. Stores information about the description of task and whether it is done or not.
 */
public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    /**
     * @return a String depending on whether the task is marked as done or not
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Changes the status of the task from 'not done' to 'done' or vice versa
     */
    public void changeStatus() {
        if (this.isDone) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.taskDescription;
    }
}
