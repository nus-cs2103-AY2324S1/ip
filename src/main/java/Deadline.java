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

    /**
     * Returns the String representation of this Deadline, for the purposes of saving.
     *
     * @return the String representation of this Deadline.
     */
    @Override
    public String toSaveString() {
        return String.format("D|%s|%s|", super.toSaveString(), this.deadline);
    }

    /**
     * Returns a new Deadline from a Save String.
     *
     * @return the Deadline that this String is represented by.
     */
    public static Deadline fromSaveString(String s) {
        String[] splitString = s.split(Task.SAVE_STRING_DELIMITER);
        // Not enough arguments; minmally, it needs the Type, the Marked status, the Name and the Deadline.
        if (splitString.length < 4) {
            return null;
        }

        TaskStatus taskStatus = TaskStatus.UNMARKED;
        String name = "";
        String deadline = "";

        if(Integer.parseInt(splitString[1]) == 1) {
            taskStatus = TaskStatus.MARKED;
        }

        name = splitString[2];
        deadline = splitString[3];

        return new Deadline(name, deadline, taskStatus);
    }
}
