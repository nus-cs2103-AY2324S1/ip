package task;

import java.time.format.DateTimeFormatter;

/**
 * Super class for all tasks used in Bocchi
 */
public abstract class Task {
    protected static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    protected static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss");
    protected final String name;
    private final boolean isMarked;

    public Task(String name, boolean marked) {
        this.name = name;
        this.isMarked = marked;
    }

    public Task(String name) {
        this(name, false);
    }

    /**
     * Marks the task.
     *
     * @return Marked task
     */
    abstract Task mark();

    /**
     * Unmarks the task.
     *
     * @return Unmarked task
     */
    abstract Task unmark();

    /**
     * Returns the name of the task
     *
     * @return Name of task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Creates the string to be saved into the save file
     *
     * @return String to be saved
     */
    public String saveTask() {
        return String.format("%s | %s", this.isMarked ? "1" : "0", this.name);
    }

    /**
     * Returns the name of task to be done.
     *
     * @return Name of task to be done
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.isMarked ? "X" : " ", this.name);
    }
}
