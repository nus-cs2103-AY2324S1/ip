package duke.tasks;

import java.time.LocalDateTime;

import duke.components.Status;

/**
 * Encapsulates a Task. Contains methods that allows users to interact with a Task
 * object, such as marking, unmarking or deleting tasks.
 */
public abstract class Task {
    private Status status;
    private String task;

    /**
     * Class constructor for Task.
     *
     * @param status either DONE or NOT_DONE
     * @param task   task description.
     */
    public Task(Status status, String task) {
        this.status = status;
        this.task = task;
    }

    public abstract String convertTask();

    public abstract boolean isWithin(LocalDateTime start, LocalDateTime end);

    /**
     * Marks this task as completed.
     *
     * @return true if the task is marked successfully.
     */
    public boolean canMark() {
        if (this.status == Status.NOT_DONE) {
            this.status = Status.DONE;
            return true;
        }
        return false;
    }

    /**
     * Marks this task as uncompleted.
     *
     * @return true if task is unmarked successfully.
     */
    public boolean canUnMark() {
        if (this.status == Status.DONE) {
            this.status = Status.NOT_DONE;
            return true;
        }
        return false;
    }

    /**
     * Returns the status of the task.
     *
     * @return a task is either DONE or NOT_DONE.
     */
    public Status getStatus() {
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
        if (this.status == Status.NOT_DONE) {
            return "[ ] " + task;
        } else {
            return "[X] " + task;
        }
    }
}
