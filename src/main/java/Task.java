

/**
 * Tasks are the tasks sent by the user to the Duke class
 */
public class Task {
    /**The name of the task.*/
    private String taskname;
    /**Status on whether the task is done or not.*/
    private boolean taskstatus;

    /**Instantiates an instance of a task.
     * @param taskname takes in the name of the task.*/
    public Task(String taskname) {
        this.taskname = taskname;
        this.taskstatus = false;
    }

    /**
     * Sets the current task as done.
     */
    public void done() {
        this.taskstatus = true;
    }

    /**
     * Sets the current task as not done.
     */
    public void undo() {
        this.taskstatus = false;
    }

    /**
     * Shows the name of the task and its status.
     *
     * @return Name of task and its current status.
     */
    @Override
    public String toString() {
        String status = taskstatus ? "[X]" : "[ ]";

        return status + " " + taskname;
    }


}
