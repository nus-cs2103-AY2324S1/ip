package duke;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private String by;
    private LocalDateTime dateTime;

    /**
     * Constructor for Deadline, if it is not supplied with a dateTime.
     *
     * @param name Description of the Deadline.
     * @param by Description of when the Deadline is due.
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    /**
     * Constructor for Deadline, if it is not supplied with a dateTime but its
     * done status is specified.
     *
     * @param name Description of the Deadline.
     * @param by Description of when the Deadline is due.
     * @param isDone Boolean representing whether the task is done.
     */
    public Deadline(String name, String by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    /**
     * Constructor for Deadline if it is supplied with a dateTime.
     *
     * @param name Description of the Deadline.
     * @param dateTime dateTime object representing when the Deadline is due.
     */
    // Constructor for duke.Deadline with date and time
    public Deadline(String name, LocalDateTime dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    /**
     * Constructor for Deadline if it is supplied with a dateTime and done status.
     *
     * @param name Description of the Deadline.
     * @param dateTime dateTime object representing when the Deadline is due.
     * @param isDone Boolean representing whether the task is done.
     */
    public Deadline(String name, LocalDateTime dateTime, boolean isDone) {
        super(name, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Gets the String representation of the Deadline.
     *
     * @return String representation of the Deadline.
     */
    public String toString() {
        if (by == null) {
            return "[D]" + super.toString() + " (by: " + DateManager.dateTimeToString(dateTime) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    /**
     * Gets the String representation of the Deadline in the storage.
     *
     * @return String representation of the Deadline in the storage.
     */
    public String toStringStorage() {
        String nameField = this.getName();
        String isDoneField = this.isDone() ? "1" : "0";
        String deadlineField = by == null ? DateManager.dateTimeToStringStorage(this.dateTime) : by;
        return "D|" + isDoneField + "|" + nameField + "|" + deadlineField;
    }
}