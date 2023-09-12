package duke.task;

/**
 * Represents a timed task to be used by the Duke chat-bot. A timed tasks is one that takes a fixed
 * amount of time but does not have a fixed start or end time.
 */
public class Timed extends Task {
    protected float duration;

    /**
     * Creates a Timed object. A timed tasks is one that takes a fixed amount of time but does not
     * have a fixed start or end time.
     *
     * @param description The description of the task.
     * @param duration    The duration of the task in hours.
     */
    public Timed(String description, float duration) {
        super(description, false);
        this.duration = duration;
    }

    /**
     * Creates a Timed object with done status as specified. A timed tasks is one that takes a fixed
     * amount of time but does not have a fixed start or end time.
     *
     * @param description The description of the task.
     * @param duration    The duration of the task in hours.
     * @param isDone      Whether the task is done.
     */
    public Timed(String description, boolean isDone, float duration) {
        super(description, isDone);
        this.duration = duration;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[I]%s (duration: %.1f hours)", super.toString(), this.duration);
    }

    /**
     * Returns the task encoded for storage for the Duke chat-bot
     *
     * @return The task encoded for storage for the Duke chat-bot.
     */
    @Override
    public String encodeTask() {
        return String.format("I;%s;%s;%f", this.isDone ? "X" : " ", this.description, this.duration);
    }
}
