import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDate date;

    public Deadline(String name, String by) {
        super(name);
        this.date = LocalDate.parse(by, DateTimeFormatter.ofPattern("d MMM yyyy"));

    }

    @Override
    public String toString() {
        String dateString = this.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return "[D]" + super.toString() + " (by: " + dateString + ")";
    }

    @Override
    public String taskToStringStore(Task task) {
        String dateString = this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "D-" + super.taskToStringStore(task) + dateString;
    }
}
