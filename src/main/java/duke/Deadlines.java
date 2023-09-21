package duke;

/**
 * Represents a deadline task with a specified due date.
 */
public class Deadlines extends Task {
    /** The raw date string representing the deadline. */
    protected String date;

    /** The parsed DateTime object representation of the deadline date. */
    protected DateTime dt;

    /**
     * Initializes a Deadlines task with the given description and due date.
     *
     * @param description The description of the deadline task.
     * @param date The due date of the deadline task.
     */
    public Deadlines(String description, String date) {
        super(description);

        assert description != null && !description.trim().isEmpty() : "Description should not be null or empty";
        assert date != null && !date.trim().isEmpty() : "Date should not be null or empty";

        this.date = date;
        this.dt = new DateTime(date);
    }

    /**
     * Initializes a Deadlines task with the given description, due date, completion status, and notes.
     *
     * @param description The description of the deadline task.
     * @param date The due date of the deadline task.
     * @param isDone The completion status of the deadline task.
     * @param notes Additional notes associated with the deadline task.
     */
    public Deadlines(String description, String date, Boolean isDone, String notes) {
        super(description, isDone, notes);

        assert description != null && !description.trim().isEmpty() : "Description should not be null or empty";
        assert date != null && !date.trim().isEmpty() : "Date should not be null or empty";

        this.date = date;
        this.dt = new DateTime(date);
    }

    /**
     * Returns the formatted string used for saving this deadline task to storage.
     *
     * @return The formatted string representation for saving.
     */
    @Override
    public String getSavingFormat() {
        // Assert that all necessary data members are not null before formatting.
        assert date != null && !date.trim().isEmpty() : "Date should not be null or empty when saving";

        //task type + status + description + date + notes
        return "[D] | [" + getStatusIcon() + "] | "
                + description + " | " + date + " | " + getNotes();
    }

    /**
     * Converts a split string array into a Deadlines object.
     * This method is typically used for parsing saved data from storage to recreate a Deadlines task.
     *
     * @param split The split string array containing the task data.
     * @return A Deadlines object constructed from the provided split string.
     */
    public static Deadlines toTask(String[] split) {
        return new Deadlines(split[2], split[3], split[1].contains("[X]"), split.length == 5 ? split[4] : "");
    }

    /**
     * Returns the string representation of this deadline task.
     *
     * @return The formatted string representation.
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon()
                + "] " + description + " (by: " + dt + ")";
    }
}
