public class Task {
    /**
     * The description of the task
     */
    private final String task;

    /**
     * The state of the task
     */
    private boolean completed = false;

    /**
     * Input that generated the task
     */
    private final String input;

    /**
     * Constructor for the Task class
     *
     * @param task  - the description of the task created
     * @param input - Input that generated the task
     */
    public Task(String task, String input) {
        this.task = task;
        this.input = input;
    }

    /**
     * Accessor for the completed field
     *
     * @return true if completed is true
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Toggles the complete field
     */
    public void toggleCompleted() {
        this.completed = !this.completed;
    }

    public String getInput() {
        return input;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", completed ? "X" : " ", this.task);
    }
}
