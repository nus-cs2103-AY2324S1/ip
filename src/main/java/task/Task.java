package task;

import dukeexceptions.DukeException;

/**
 * This is an abstract class that contain the factory methods and all the methods that a task will have.
 */
public abstract class Task {

    private final String taskDescription;
    private Boolean isDone;


    Task(String taskName) {
        this.taskDescription = taskName;
        this.isDone = false;
    }

    /**
     * The string that represents the object.
     *
     * @return Returns a string that represents the object.
     */
    @Override
    public String toString() {
        return ("[" + (this.isDone ? "X] " : " ] ") + this.taskDescription);
    }

    /**
     * Mark the task as done.
     */
    public void setDone() throws DukeException {
        if (isDone) {
            throw new DukeException("Task is already done");
        }
        this.isDone = true;
    }

    /**
     * Mark the task as undone.
     */
    public void setNotDone() throws DukeException {
        if (!isDone) {
            throw new DukeException("Task is still not done");
        }
        this.isDone = false;
    }

    /**
     * Check if the task is done.
     *
     * @return Returns true if the task is done, false if otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the name of the task.
     *
     * @return Returns the name of the task.
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Writes the initial command the user put that resulted in the task.
     *
     * @return Returns the initial command the user put for this task.
     */
    public String write() {
        return "task " + this.taskDescription + "\n";
    }
}

