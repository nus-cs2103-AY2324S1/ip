package duke;

public class Task {
    private String name;
    private boolean isDone = false;

    /**
     * Constructor for Task.
     *
     * @param name Description of Task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Constructor for Task if it is supplied with a done status.
     *
     * @param name Description of Task.
     * @param isDone Boolean representing whether the task is done.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Checks if the task is done.
     *
     * @return Boolean representing whether the task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the String representation of the Task.
     *
     * @return String representation of the Task.
     */
    public String toString() {
        String checkbox = isDone ? "[X] " : "[ ] ";
        return checkbox + name;
    }

    /**
     * Marks Task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Gets the name of the Task.
     *
     * @return Name of the Task.
     */
    public String getName() {
        return this.name;
    }
}
