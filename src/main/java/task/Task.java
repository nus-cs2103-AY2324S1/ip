package task;

/**
 * The task.Task Class is an abstract class that serves as a base class for various types of task the user can create.
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Constructs a new task.Task with the specified name. By default,
     * the task is marked as not done.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructs a new task.Task with the specified name.
     * Task constructed can be marked as completed.
     *
     * @param name The name of the task.
     * @param isDone If task is completed.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Returns a string representing if the task is marked as done or not.
     *
     * @return The string message if task is done.
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as done.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns the string description of the current task. Description includes if task is done and the task name.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.name;
    }

    /**
     * Returns string of task to store in file. Description includes
     * task type, date, if task is done and the task name.
     */
    public String fileString() {
        String done = isDone ? "1" : "0";
        return " | " + done + " | " + this.name;
    }
}
