package duchess;

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

    /**
     * Returns the String representation of this Event, for the purposes of saving.
     *
     * @return the String representation of this Event.
     */
    @Override
    public String toSaveString() {
        return String.format("E|%s|%s|%s|", super.toSaveString(), this.startTime, this.endTime);
    }

    /**
     * Returns a new Event from a Save String.
     *
     * @return the Event that this String is represented by.
     */
    public static Event fromSaveString(String s) {
        String[] splitString = s.split(Task.SAVE_STRING_DELIMITER);
        // Not enough arguments; minmally, it needs the Type, the Marked status, the Name, the Start Time,
        // and the End Time.
        if (splitString.length < 5) {
            return null;
        }

        TaskStatus taskStatus = TaskStatus.UNMARKED;
        String name = "";
        String startTime = "";
        String endTime = "";

        if (Integer.parseInt(splitString[1]) == 1) {
            taskStatus = TaskStatus.MARKED;
        }

        name = splitString[2];
        startTime = splitString[3];
        endTime = splitString[4];

        return new Event(name, startTime, endTime, taskStatus);
    }
}
