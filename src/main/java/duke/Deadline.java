package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a specific deadline. It is a subclass of the Task class.
 * It can have either a LocalDate or LocalDateTime deadline, and it can be marked as done or undone.
 */
public class Deadline extends Task {
    //fields

    protected String by;
    protected boolean isDone = false;
    /**
     * The date of the deadline for the task when it is represented as a LocalDate.
     */
    protected LocalDate date;
    /**
     * The date and time of the deadline for the task when it is represented as a LocalDateTime.
     */
    protected LocalDateTime dateTime;
    /**
     * An integer representing the mode of time representation:
     * 1 for LocalDate, 2 for LocalDateTime, and 0 for custom format (stored as a string).
     */
    protected int modeOfTime;
    /**
     * The DateTimeFormatter used to format the date when it is represented as "dd/MM/yyyy".
     */
    protected DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    /**
     * The DateTimeFormatter used to format the date and time when it is represented as "dd/MM/yyyy HHmm".
     */
    protected DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    // Constructors

    /**
     * Constructs a Deadline object with a description and a LocalDate deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline represented as a LocalDate.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.modeOfTime = 1;
        this.date = by;
    }
    /**
     * Constructs a Deadline object with a description and a LocalDateTime deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline represented as a LocalDateTime.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.modeOfTime = 2;
        this.dateTime = by;
    }

    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description);
        this.isDone = isDone;
        this.modeOfTime = 1;
        this.date = by;
    }

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description);
        this.isDone = isDone;
        this.modeOfTime = 2;
        this.dateTime = by;
    }

    public Deadline(String description, LocalDate by, boolean isDone, int modeOfTime) {
        super(description);
        this.isDone = isDone;
        this.modeOfTime = modeOfTime;
        this.date = by;
    }

    public Deadline(String description, LocalDateTime by, boolean isDone, int modeOfTime) {
        super(description);
        this.isDone = isDone;
        this.dateTime = by;
        this.modeOfTime = modeOfTime;
    }

    private String dateAsString() {
        if (this.modeOfTime == 1) {
            return this.date.format(formatDate);
        } else if (this.modeOfTime == 2) {
            return this.dateTime.format(formatDateTime);
        } else {
            return this.by;
        }
    }

    private String dateAsSavedString() {
        if (this.modeOfTime == 1) {
            return this.date.toString();
        } else if (this.modeOfTime == 2) {
            return this.dateTime.toString();
        } else {
            return this.by;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateAsString() + ")";
    }

    @Override
    public String toSaveString() {
        String divider = " | ";
        return "D" + divider + super.toSaveString() + divider + this.dateAsSavedString() + divider + this.modeOfTime;
    }

    /**
     * Returns the date of the deadline based on the mode of time representation.
     * If the mode is 1 (LocalDate), it returns a LocalDate; if the mode is 2 (LocalDateTime),
     * it converts the LocalDateTime to a LocalDate and returns it; otherwise, it returns null.
     *
     * @return The date of the deadline.
     */
    public LocalDate getDate(){
        if (this.modeOfTime == 1){
            return date;
        } else if (this.modeOfTime == 2) {
            return dateTime.toLocalDate();
        }
        else{
            return date;
        }
    }
}