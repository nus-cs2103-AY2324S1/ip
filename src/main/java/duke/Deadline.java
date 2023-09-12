package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    private LocalDate deadline;



    public Deadline(String name, boolean isMarked, LocalDate deadline) {
        super(name, isMarked);
        this.deadline = deadline;
    }



    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String status = isMarked ? "[X]" : "[ ]";
        return "[D]" + status + " " + name + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
