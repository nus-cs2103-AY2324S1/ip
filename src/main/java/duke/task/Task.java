package duke.task;

import duke.utility.Ui;

/**
 * Represents a task that can be marked as done or undone.
 */
public abstract class Task {

    private final String name;
    private boolean isDone;

    /**
     * Constructs a Task object with a specified name.
     *
     * @param name The name or description of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks the task as done and optionally displays a message.
     *
     * @param willDisplayMessage Whether to display a message indicating the task was marked as done.
     */
    public void markAsDone(boolean willDisplayMessage, Ui ui) {
        this.isDone = true;
        if (willDisplayMessage) {
            ui.printMessage("\nNice! I've marked this task as done:\n  " + this + "\n");
        }
    }

    /**
     * Unmarks the task as done and optionally displays a message.
     *
     * @param willDisplayMessage Whether to display a message indicating the task was marked as not done.
     */
    public void unmarkAsDone(boolean willDisplayMessage) {
        this.isDone = false;
        if (willDisplayMessage) {
            System.out.println("\nNice! I've unmarked this task as done:\n  " + this + "\n");
        }
    }

    @Override
    public String toString() {
        char markDone = this.isDone ? 'X' : ' ';
        return "[" + markDone + "] " + this.name;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Converts the task into a formatted string for storage.
     *
     * @return The formatted string representation of the task.
     */
    public abstract String convertTaskToStorageFormat();

}
