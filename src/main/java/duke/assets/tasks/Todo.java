package duke.assets.tasks;

/**
 * Represents a todo task
 */
public class Todo extends TaskAbstract {
    /**
     * Constructs a todo task with the given description
     *
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the todo task in text format for saving to file
     *
     * @return the todo task in text format for saving to file
     */
    public String saveToTextFormat() {
        return String.format("T | %s | %s", this.isDone ? "1" : "0",
                this.description);
    }

    /**
     * Get the status of the todo task as a string
     */
    @Override
    public String getStatus() {
        return String.format("[T][%s] %s\n", this.isDone ? "X" : " ", this.description);
    }
}
