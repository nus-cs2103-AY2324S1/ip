import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task{
    private String deadline;
    private LocalDate deadDate;
    public Deadline(String name, String deadline) {
        super(name);
        this.type = "D";
        this.deadline = deadline;
    }
    public Deadline(String name, LocalDate deadDate) {
        super(name);
        this.type = "D";
        this.deadDate = deadDate;
    }
    @Override
    public String toString() {
        return super.toString() + "(by: " + (deadline.isEmpty() ? deadDate : deadline) +")";
    }
}
