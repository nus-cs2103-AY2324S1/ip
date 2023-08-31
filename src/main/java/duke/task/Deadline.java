package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public static Deadline readFromFile(String[] components) {
        boolean isDone = components[1].equals("1");
        Deadline deadline = new Deadline(components[2], LocalDateTime.parse(components[3]));
        if(isDone) {
            deadline.markDone();
        }
        return deadline;
    }
    @Override
    public String writeFileFormat() {
        return "D|" + super.writeFileFormat() + "|" + this.deadline;
    }
    @Override
    public String toString() {
        return "[D] "
                + super.toString()
                + "(by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + ")";
    }
}
