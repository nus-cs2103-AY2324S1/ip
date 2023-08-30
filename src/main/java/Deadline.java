import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime date;
    private DateTimeFormatter formatter;

    public Deadline(String name, LocalDateTime date) {
        super(name);
        this.date = date;
    }

    @Override
    public String getTaskDesc() {
        if (!this.getDone()) {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
            return "D |" + " 0 | " + this.getName() + "| " + this.date.format(formatter);
        } else
            return this.getName() + "| " + this.date.format(formatter);
    }

    @Override
    public String toString() {
        if (!this.getDone()) {
            System.out.println("TaskDesc: " + this.getTaskDesc());
            return "[D][ ] " + this.getName() + "(by: " + this.date.format(formatter) + ")";
        } else {
            return "[D][X] " + this.getName() + "(by: " + this.date.format(formatter) + ")";
        }
    }
}

