class ToDo extends Task {

    /**
     * Creates a new ToDo instance with the provided name and status.
     *
     * @param name   - the name of the ToDo.
     * @param status - the current task status of the ToDo.
     */
    public ToDo(String name, TaskStatus status) {
        super(name, status);
    }

    /**
     * Creates a new ToDo instance with the provided name. The status will be the default status in Task.
     *
     * @param name   - the name of the ToDo.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns the String representation of this ToDo.
     *
     * @return the String representation of this ToDo.
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
