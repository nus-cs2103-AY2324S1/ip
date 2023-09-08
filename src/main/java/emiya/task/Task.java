package emiya.task;

/**
 * An abstract class the other Task subtypes inherit from.
 */
public abstract class Task {
    protected boolean isCompleted;
    protected String taskDescription;

    public Task(boolean isCompleted, String taskDescription) {
        this.isCompleted = isCompleted;
        this.taskDescription = taskDescription;
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return "[X] " + taskDescription;
        } else {
            return "[ ] " + taskDescription;
        }
    }

    /**
     * Returns a String that indicates what type of task it is.
     * @return Returns a String that indicates what type of task it is.
     */
    public abstract String typeOfString();

    /**
     * Returns a String that indicates whether the task is completed or not.
     * @return Returns a String that indicates whether the task is completed or not.
     */
    public String printStatusString() {
        if (isCompleted) {
            return "1 ";
        } else {
            return "0 ";
        }
    }

    /**
     * Returns a String that contains the details of the task.
     * @return Returns a String that contains the details of the task.
     */
    public abstract String printTaskDetailsString();

    public void setUnmarked() {
        this.isCompleted = false;
    }

    public void setMarked() {
        this.isCompleted = true;
    }

}
