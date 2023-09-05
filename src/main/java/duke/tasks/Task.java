package duke.tasks;

abstract public class Task {
    private int status;
    private String task;

    /**
     * Class constructor for Task.
     *
     * @param status 0 for uncompleted, 1 or other number for completed
     * @param task task description
     */
    public Task(int status, String task) {
        this.status = status;
        this.task = task;
    }

    abstract public String convertTask();

    /**
     * Marks this task as completed. Return false if the task is already
     * marked.
     *
     * @return true if the task is marked successfully
     */
    public boolean canMark() {
        if (this.status == 0) {
            this.status = 1;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Unmarks this task. Returns false if the task is not marked yet.
     *
     * @return true if task if unmarked
     */
    public boolean canUnMark() {
        if (this.status != 0) {
            this.status = 0;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the status of the task. 0 for uncompleted,
     * 1 or any other number for uncompleted.
     * @return
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * Returns the task description.
     *
     * @return task description
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Returns the string representation of a task object.
     *
     * @return a string task
     */
    @Override
    public String toString() {
        if (status == 0) {
            return "[ ] " + task;
        } else {
            return "[X] " + task;
        }
    }
}
