package Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;
    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }
    public Deadline(String name, boolean completeStatus, LocalDate deadline) {
        super(name, completeStatus);
        this.deadline = deadline;
    }
    public String toString(){
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
    public String fileFormat() {
        return "DL" + divider + super.fileFormat() + divider + deadline;
    }
}
