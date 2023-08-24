class Event extends Task {
    private String startTime;
    private String endTime;

    /**
     * Creates a new Event instance with the provided name, start time, end time and status.
     *
     * @param name      - the name of the Event.
     * @param startTime - the startTime in String format.
     * @param endTime   - the endTime in String format.
     * @param status    - the current task status of the Event.
     */
    public Event(String name, String startTime, String endTime, TaskStatus status) {
        super(name, status);

        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates a new Event instance with the provided name, start time, end time and status. The status 
     * will be the default status in Task.
     *
     * @param name      - the name of the Event.
     * @param startTime - the startTime in String format.
     * @param endTime   - the endTime in String format.
     */
    public Event(String name, String startTime, String endTime) {
        super(name);

        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the String representation of this Event.
     *
     * @return the String representation of this Event.
     */
    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), this.startTime, this.endTime);
    }
}
