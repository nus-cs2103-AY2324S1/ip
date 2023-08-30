import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.StringJoiner;

public class Deadline extends Task {
    private String by;
    private LocalDate date;
    private LocalTime time;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
        String[] dueDateTime = by.split(" ");
        this.date = LocalDate.parse(dueDateTime[0]); // input format "2016-06-11"
        this.time = LocalTime.parse(dueDateTime[1]); // input format "06:30"
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    @Override
    public String toFileFormat() {
        StringJoiner joiner = new StringJoiner(";");
        joiner.add("D").add(super.toFileFormat()).add(this.by);
        return joiner.toString();
    }
}
