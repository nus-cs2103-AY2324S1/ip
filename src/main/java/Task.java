abstract class Task {
    protected final String name;
    private final boolean marked;

    Task(String name, boolean marked) {
        this.name = name;
        this.marked = marked;
    }

    Task(String name) {
        this(name, false);
    }

    /**
     * Mark the task.
     *
     * @return Returns a marked Task
     */
    abstract Task mark();

    /**
     * Unmark the task.
     *
     * @return Returns an unmarked task
     */
    abstract Task unmark();

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
