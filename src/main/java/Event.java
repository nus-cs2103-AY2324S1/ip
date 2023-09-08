import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    private Event(String description, LocalDate startTime, LocalDate endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String description, LocalDate startTime, LocalDate endTime, boolean isDone) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDone = isDone;
    }

    public static Event createNewEventTask(String description) {
        String[] splitMessage1 = description.split(" /from ");
        String[] splitMessage2 = splitMessage1[1].split(" /to ");
        return new Event(splitMessage1[0], LocalDate.parse(splitMessage2[0]), LocalDate.parse(splitMessage2[1]));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " +
                this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
