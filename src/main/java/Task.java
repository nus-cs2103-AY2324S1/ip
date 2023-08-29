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

    public String toString() {
        return this.getStatusIcon() + " " + this.taskDescription;
    }
}
