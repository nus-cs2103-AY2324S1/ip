package task;

public abstract class Task {
    protected final String name;
    private final boolean marked;

    public Task(String name, boolean marked) {
        this.name = name;
        this.marked = marked;
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
     * Creates the string to be saved into the save file
     *
     * @return String to be saved
     */
    public String saveTask() {
        return String.format("%s | %s", this.name, this.marked ? "1" : "0");
    }

    /**
     * Returns the name of task to be done.
     *
     * @return Name of task to be done
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.marked ? "X" : " ", this.name);
    }
}
