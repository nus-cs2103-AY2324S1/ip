package bot.task;

import java.time.LocalDateTime;

public abstract class Task {
    private static final String COMPLETE = "[X] ";
    private static final String INCOMPLETE = "[ ] ";
    protected static final String UNIQUE_FILE_SEPARATOR = " &##& ";
    private final String name;
    protected boolean isDone;


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
        assert isDone : "isDone is not set correctly";
    }

    /**
     * Sets the state of the Task object to be incomplete
     */
    public void setIncomplete() {
        this.isDone = false;
        assert !isDone : "isDone is not set correctly";
    }

    /**
     * Returns true if pattern is present in task name (description), false otherwise
     *
     * @param pattern String pattern to find
     * @return boolean based on presence of pattern in task name
     */
    public boolean hasPattern(String pattern) {
        return this.name.contains(pattern);
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
    }

    /**
     * Checks if the task associated needs to be done within dateline
     *
     * @param dateTime The current date and time to compare against.
     * @return {@code true} if task can be done within dateTime, {@code false} otherwise.
     */
    abstract boolean canDoWithin(LocalDateTime dateTime);

    /**
     * Converts the Task object into a String representation
     *
     * @return String representation of Task
     */
    @Override
    public String toString() {
        if (isDone) {
            return Task.COMPLETE + name;
        } else {
            return Task.INCOMPLETE + name;
        }
    }
}
