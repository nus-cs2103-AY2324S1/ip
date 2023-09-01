package duke.Task;

import duke.DukeException.DukeException;

/**
 * Represnt a task.
 */
public abstract class Task {
    private boolean isDone;
    private String name;

    /**
     * Create a task.
     * @param name Description of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Change the status to a new status
     * @param newStatus New Status that the task will have.
     */
    public void changeMarkStatus(boolean newStatus) {
        this.isDone = newStatus;
    }

    /**
     * Print task as a string.
     * @return String representing the task.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    public String getName() {
        return this.name;
    }

    public Boolean getMarkStatus() {
        return this.isDone;
    }
    public abstract String writeString();
    /**
     * Check whether the input is a task.
     * @param input Task to be checked.
     * @return Boolean that represent whether the input is a task.
     * @throws DukeException Exception where the input is not a task.
     */
    public static boolean isTask(String input) throws DukeException {
        if(input.split( " ")[0].equals("todo") || input.split( " ")[0].equals("deadline") || input.split( " ")[0].equals("event")) {
            return true;
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means");
        }
    }
}