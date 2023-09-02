package phi;

/**
 * Abstract parent class of all task types. Cannot be instantiated.
 */
public abstract class Task {
    protected enum Type {T, D, E}

    protected Type taskType;
    protected boolean done;
    protected String taskName;

    /**
     * Constructor for a new Task
     *
     * @param taskType  Type of task (ToDo, Deadline, Event) in enumerated type
     * @param done      Boolean determining if task is completed
     * @param taskName  Task message to be displayed
     *
     */
    public Task(Type taskType, boolean done, String taskName) {
        this.taskType = taskType;
        this.done = done;
        this.taskName = taskName;
    }

    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }

    @Override
    public String toString() {
        return done
                ? String.format("[%s][X] %s", taskType, taskName)
                : String.format("[%s][ ] %s", taskType, taskName);
    }

    /**
     * Returns the task in a String, formatted in the style that will be written to storage
     * @return String representation of the task in output format
     */
    public abstract String outputFormat();
}
