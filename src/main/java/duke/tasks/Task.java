package duke.tasks;

/**
 * Encapsulates a Task. Contains methods that allows users to interact with a Task
 * object, such as marking, unmarking or deleting tasks.
 */
public abstract class Task {
    private int status;
    private String task;

    /**
     * Class constructor for Task.
     *
     * @param status 0 for uncompleted, 1 or other number for completed.
     * @param task   task description.
     */
    public Task(int status, String task) {
        this.status = status;
        this.task = task;
    }

    public abstract String convertTask();

    /**
     * Marks this task as completed.
     *
     * @return true if the task is marked successfully.
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
     * Marks this task as uncompleted.
     *
     * @return true if task is unmarked.
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
     * Returns the status of the task.
     *
     * @return 0 if uncompleted, 1 or other number for completed.
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * Returns the task description.
     *
     * @return task description.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Returns the string representation of a task object.
     *
     * @return a string task.
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
