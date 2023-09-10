import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime deadline;

    //Constructor
    public Deadline(String name, String deadline) {
        super(name);
        this.isCompleted = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String desiredFormat = this.deadline.format(formatter);
        return "[D]" + super.toString() + "(by: " + desiredFormat + ")";
    }
}


