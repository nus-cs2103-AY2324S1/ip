package ducky.task;

/**
 * Represents a task stored in Ducky chatbot memory.
 */
public abstract class Task {

    private final String description;
    private boolean completed;

    /**
     * Constructs a task with specified description and incomplete by default.
     * @param desc Description of task.
     */
    public Task(String desc) {
        this.description = desc;
        this.completed = false;
    }

    /**
     * Sets the task status to complete.
     */
    public void setComplete() {
        this.completed = true;
    }

    /**
     * Sets the task status to incomplete.
     */
    public void setIncomplete() {
        this.completed = false;
    }

    /**
     * Returns a representation of the task for printing to user interface.
     * @return Representation of the task meant for printing.
     */
    @Override
    public String toString() {
        if (this.completed) {
            return String.format("[X] %s", this.description);
        }
        return String.format("[ ] %s", this.description);
    }

    /**
     * Returns a representation of the task for saving to file system.
     * @return Representation of the task meant for saving.
     */
    public String getSaveFormat() {
        return String.format(
                "%d | %s",
                this.completed ? 1 : 0,
                this.description
        );
    }
}
