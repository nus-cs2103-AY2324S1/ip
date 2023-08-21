
public class Task {
    /**
     * The description of the task
     */
    private String task;

    /**
     * The state of the task
     */
    private boolean completed = false;

    /**
     * The Index of the task
     */
    private int index;

    /**
     * Constructor for the Task class
     * 
     * @param task  - the description of the task created
     * @param index - the id of the task created
     */
    public Task(String task, int index) {
        this.task = task;
        this.index = index;
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

    @Override
    public String toString() {
        return String.format("[%s] %s", completed ? "X" : " ", this.task);
    }
}
