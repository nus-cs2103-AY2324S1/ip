import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public  class Deadline extends Task {
    private LocalDateTime deadline;

    String datePattern = "MMM-dd-yyyy HH:mm";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getDescription() {
        return super.getDescription() + " (by: " + deadline.format(formatter) + ")";
    }

    public String getDeadline() {
        return deadline.format(formatter);
    }

    public String getDescriptionWithoutTime() {
        return super.getDescription();
    }
}