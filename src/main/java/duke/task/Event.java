package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public static Event readFromFile(String[] components) {
        boolean isDone = components[1].equals("1");
        Event event = new Event(components[2], LocalDateTime.parse(components[3]),
                LocalDateTime.parse(components[4]));
        if(isDone) {
            event.markDone();
        }
        return event;
    }
    @Override
    public String writeFileFormat() {
        //store as E|1/0|this.start|this.end
        return "E|" + super.writeFileFormat() + "|" + this.start + "|" + this.end;
    }
    @Override
    public String toString() {
        return "[E] "
                + super.toString()
                + "(from: "
                + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " to: "
                + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + ")";
    }
}
