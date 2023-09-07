package tasks;

/**
 * A parent class that represents the task that the user will input.
 */
public class Task {
    private String type;
    private final String name;
    private boolean marked;

    /**
     * A Constructor method for task.
     * @param name the name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.marked = false;
    }
    public void setType(String type) {
        this.type = type;
    }

    /**
     * A method that will mark a task as marked.
     */
    public void markDone() {
        marked = true;
    }

    /**
     * A method that will unmark a task.
     */
    public void unmarkDone() {
        marked = false;
    }
    /**
     * A method that will represent the task.
     *
     * @return the syntax that will be shown to the user.
     */
    @Override
    public String toString() {
        return "  [" + type + "]" + "[" + (marked ? "X" : " ") + "] " + name;
    }
}
