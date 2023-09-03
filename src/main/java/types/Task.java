package types;

/**
 * Represents all Tasks that can be added into the list of Tasks saved by Barbie.
 */
public class Task {
    protected boolean isCompleted;
    protected String description;

    /**
     * Initialises a Task, that has not yet been completed.
     *
     * @param description description of the Task to be completed
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Retrieves the status of the Task: done or undone.
     *
     * @return a String of [X] or [ ], [X] for done and [ ] for undone.
     */
    public String getStatus() {
        return (isCompleted ? "[X]" : "[ ]");
    }

    /**
     * Unmarks a Task as done.
     * Meaning that the Task is marked as undone
     */
    public void unmark() {
        isCompleted = false;
    }

    /**
     * Marks the Task as done.
     */
    public void mark() {
        isCompleted = true;
    }

    /**
     * Overrides the toString() method to return a customised String with the status of the Task.
     *
     * @return customised String including the status and description of the Task
     */
    @Override
    public String toString() {
        return this.getStatus() + " " + description;
    }

}
