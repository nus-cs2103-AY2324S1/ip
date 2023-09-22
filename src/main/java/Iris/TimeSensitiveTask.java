package iris;

/**
 * An abstract class representing a task that is time-sensitive.
 */
public abstract class TimeSensitiveTask extends Task {

    /**
     * Creates a new time-sensitive task with the given name.
     *
     * @param name The name of the task.
     */
    TimeSensitiveTask(String name) {
        super(name);
    }

    /**
     * Abstract method to postpone the task.
     *
     * @param time The new time for the task.
     */
    public abstract void postpone(String time);
}
