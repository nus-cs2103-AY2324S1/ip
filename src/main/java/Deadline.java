class Deadline extends Task {
    private String deadline;

    /**
     * Creates a new Deadline instance with the provided name, deadline and status.
     *
     * @param name     - the name of the Deadline.
     * @param deadline - the deadline in String format.
     * @param status   - the current task status of the Deadline.
     */
    public Deadline(String name, String deadline, TaskStatus status) {
        super(name, status);

        this.deadline = deadline;
    }

    /**
     * Creates a new Deadline instance with the provided name and deadline. The status will be the default status in Task.
     *
     * @param name     - the name of the Deadline.
     * @param deadline - the deadline in String format.
     */
    public Deadline(String name, String deadline) {
        super(name);

        this.deadline = deadline;
    }

    /**
     * Returns the String representation of this Deadline.
     *
     * @return the String representation of this Deadline.
     */
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline);
    }
}
