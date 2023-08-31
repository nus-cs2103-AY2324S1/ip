package Duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate deadline;
    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    public String toString(){
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
