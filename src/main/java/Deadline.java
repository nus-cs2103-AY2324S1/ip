import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private final LocalDate due;

    Deadline(String description, LocalDate due) {
        super(description);
        this.due = due;
    }

    Deadline(String description, LocalDate due, Boolean marked) {
        super(description, marked);
        this.due = due;
    }

    @Override
    String getType() {
        return "D";
    }

    @Override
    Deadline mark() {
        return new Deadline(this.description, this.due, true);
    }

    @Override
    Deadline unmark() {
        return new Deadline(this.description, this.due, false);
    }

    @Override
    Boolean happenOnThatDate(LocalDate date) {
        return date.isEqual(due);
    }

    String getDue() {
        return due.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                due.format(DateTimeFormatter.ofPattern("MMM d yyyy"))  + ")";
    }
}
