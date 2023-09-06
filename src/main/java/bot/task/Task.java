package bot.task;

public abstract class Task {
    private static final String COMPLETE = "[X] ";
    private static final String INCOMPLETE = "[ ] ";
    protected static final String UNIQUE_FILE_SEPARATOR = " &##& ";
    private final String name;
    private boolean isDone;


    /**
     * Creates an instance of a Task object
     *
     * @param name Task description
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Creates an instance of {@code Task}
     *
     * @param name Task description
     * @param isDone to mark if the task is done or not
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Sets the state of the Task object to be completed
     */
    public void setComplete() {
        this.isDone = true;
    }

    /**
     * Sets the state of the Task object to be incomplete
     */
    public void setIncomplete() {
        this.isDone = false;
    }

    /**
     * Returns a string of the task formatted to be stored in data/task.txt
     *
     * @return a String of the task
     */
    public String fileWriteFormatted() {
        if (this.isDone) {
            return "true" + Task.UNIQUE_FILE_SEPARATOR + this.name;
        } else {
            return "false" + Task.UNIQUE_FILE_SEPARATOR + this.name;
        }
    };

    /**
     * Converts the Task object into a String representation
     *
     * @return String representation of Task
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return Task.COMPLETE + this.name;
        } else {
            return Task.INCOMPLETE + this.name;
        }
    }
}
