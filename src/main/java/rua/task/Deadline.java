package rua.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate due;

    public Deadline(String description, LocalDate due) {
        super(description);
        this.due = due;
    }

    public Deadline(String description, LocalDate due, Boolean marked) {
        super(description, marked);
        this.due = due;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public Deadline mark() {
        return new Deadline(this.description, this.due, true);
    }

    @Override
    public Deadline unmark() {
        return new Deadline(this.description, this.due, false);
    }

    @Override
    public Boolean isHappeningOnThatDate(LocalDate date) {
        return date.isEqual(due);
    }

    public String getDue() {
        return due.format(DateTimeFormatter.ofPattern("MM dd yyyy"));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Deadline)) {
            return false;
        }
        Deadline c = (Deadline) o;

        // Compare the data members and return accordingly
        return c.description.equals(this.description) &&
                c.marked.equals(this.marked) &&
                c.due.isEqual(this.due);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                due.format(DateTimeFormatter.ofPattern("MM dd yyyy")) + ")";
    }
}
