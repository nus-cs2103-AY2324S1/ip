import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    public Event(boolean isDone, String taskName, LocalDateTime start, LocalDateTime end) {
        super(isDone, taskName);
        this.start = start;
        this.end = end;
    }

    public String getDateTimeString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy 'at' HH:mm a");
        return dateTime.format(formatter);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[E]"
                    + "[X] "
                    + this.taskName
                    + "(from: " + getDateTimeString(this.start)
                    + " to: " + getDateTimeString(this.end) + ")";
        } else {
            return "[E]"
                    + "[ ] "
                    + this.taskName
                    + "(from: " + getDateTimeString(this.start)
                    + " to: " + getDateTimeString(this.end) + ")";
        }
    }

    @Override
    public String toStoreString() {
        if (this.isDone) {
            return "E/@/1/@/" + this.taskName + "/@/" + this.start + "/@/" + this.end;
        } else {
            return  "E/@/0/@/" + this.taskName + "/@/" + this.start + "/@/" + this.end;
        }
    }

    @Override
    public String toUpdateString(int i) {
        return "E/@/" + i + "/@/" + this.taskName + "/@/" + this.start + "/@/" + this.end;
    }
}

