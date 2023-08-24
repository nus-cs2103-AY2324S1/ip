class Task {
    private final String name;
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
     * @return Returns a marked Task
     */
    public Task mark() {
        return new Task(this.name, true);
    }

    /**
     * Unmark the task.
     * @return Returns an unmarked task
     */
    public Task unmark() {
        return new Task(this.name, false);
    }

    /**
     * Returns the name of task to be done.
     * @return Name of task to be done
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.marked ? "X" : " ", this.name);
    }
}
