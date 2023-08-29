public class Task {

    /**
     * Separator used
     */
    public static String SEP = "#";
    /**
     * The description of the task
     */
    private final String task;

    /**
     * The state of the task
     */
    private boolean completed = false;

    /**
     * Constructor for the Task class
     *
     * @param task - the description of the task created
     */
    public Task(String task) {
        this.task = task;
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

    /**
     * Getter for tasks
     *
     * @return task string
     */
    public String getTask() {
        return task;
    }

    /**
     * returns the stored form of the task
     *
     * @return a string which Duke Parser could Parse
     */
    public String getStored() {
        return "";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", completed ? "X" : " ", this.task);
    }
}
