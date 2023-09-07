package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected boolean isDone = false;
    protected LocalDate date;
    protected LocalDateTime dateTime;
    protected int modeOfTime;
    protected DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    protected DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");


    public Deadline(String description, LocalDate by) {
        super(description);
        this.modeOfTime = 1;
        this.date = by;
    }

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

    //this.date = LocalDate.parse(by, format);
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateAsString() + ")";
    }

    @Override
    public String toSaveString() {
        String divider = " | ";
        return "D" + divider + super.toSaveString() + divider + this.dateAsSavedString() + divider + this.modeOfTime;
    }
}
