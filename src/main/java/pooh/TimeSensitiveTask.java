package pooh;

/**
 * Represents a task that is time-sensitive. This abstract class provides a foundation
 * for tasks that have a time component and can be postponed.
 */
public abstract class TimeSensitiveTask extends Task {

    /**
     * Constructs a TimeSensitiveTask with the specified description.
     *
     * @param description A brief description of the task.
     */
    TimeSensitiveTask(String description) {
        super(description);
    }

    /**
     * Postpones the task to a new specified time.
     *
     * @param time The new time to which the task should be postponed.
     */
    public abstract void postpone(String time);
}
