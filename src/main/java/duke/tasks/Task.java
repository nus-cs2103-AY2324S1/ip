package duke.tasks;

/**
 * The Task that encapsulates its description and its completion state.
 */
public abstract class Task {
    private boolean done;
    private final String name;

    private Task() {
        this.name = "";
        done = false;
    }

    /**
     * Instantiates a new Task.
     *
     * @param name the description of the Task
     */
    public Task(String name) {
        this.name = name;
        done = false;
    }

    /**
     * Sets the Task as completed.
     */
    public void setdone() {
        done = true;
    }

    /**
     * Returns the completions status of the Task.
     *
     * @return a boolean for completions status
     */
    public boolean isdone() {
        return this.done;
    }

    /**
     * Return the description of the task.
     *
     * @return the Task description
     */
    public String getname() {
        return this.name;
    }

    /**
     * Returns the String description of a task suitable for file storage.
     *
     * @return the task String
     */
    public abstract String dataString();

    @Override
    public String toString() {
        String box;

        if (done) {
            box = "[X] ";
        } else {
            box = "[ ] ";
        }

        return box + name;
    }
}
