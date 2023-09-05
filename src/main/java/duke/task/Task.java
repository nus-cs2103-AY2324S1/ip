package duke.task;

/**
 * Represents a task that can be marked as done or undone.
 */
public abstract class Task {

    private final String name;
    private boolean done;

    /**
     * Constructs a Task object with a specified name.
     *
     * @param name The name or description of the task.
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * Marks the task as done and optionally displays a message.
     *
     * @param display Whether to display a message indicating the task was marked as done.
     */
    public void markAsDone(boolean display) {
        this.done = true;
        if (display) {
            System.out.println("\nNice! I've marked this task as done:\n  " + this + "\n");
        }
    }

    /**
     * Unmarks the task as done and optionally displays a message.
     *
     * @param display Whether to display a message indicating the task was marked as not done.
     */
    public void unmarkAsDone(boolean display) {
        this.done = false;
        if (display) {
            System.out.println("\nNice! I've unmarked this task as done:\n  " + this + "\n");
        }
    }

    @Override
    public String toString() {
        char markDone = this.done ? 'X' : ' ';
        return "[" + markDone + "] " + this.name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone() {
        return this.done;
    }

    /**
     * Converts the task into a formatted string for storage.
     *
     * @return The formatted string representation of the task.
     */
    public abstract String convertTaskToString();

}
