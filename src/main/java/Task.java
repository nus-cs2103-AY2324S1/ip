public class Task {
    private String description;

    /**
     * Constructs a Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
