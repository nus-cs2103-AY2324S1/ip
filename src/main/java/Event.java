import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Event extends Task{

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    public static Event create(String rawLine) throws DukeException {
        if (rawLine.length() == 0) {
            throw new DukeException("Err: Empty Description");
        }
        String[] instructions = rawLine.split(" /from ", 2);
        if (instructions.length != 2) {
            throw new DukeException("Err: No start time given. Format is in event <desc> /from <time> /to <time>");
        }
        String[] timeLine = instructions[1].split(" /to ", 2);
        if (timeLine.length != 2) {
            throw new DukeException("Err: No end time given. Format is in event <desc> /from <time> /to <time>");
        }

        LocalDateTime startDate, endDate;

        try {
            startDate = LocalDateTime.parse(timeLine[0], Task.standardDateTimeParser);
        } catch (DateTimeException e) {
            throw new DukeException.DukeDateTimeException(timeLine[0]);
        }

        try {
            endDate = LocalDateTime.parse(timeLine[1], Task.standardDateTimeParser);
        } catch (DateTimeException e) {
            throw new DukeException.DukeDateTimeException(timeLine[1]);
        }

        return new Event(instructions[0], startDate, endDate);
    }
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String fileString() {
        return String.format(
                "event %d %s /from %s /to %s",
                super.isDone ? 1 : 0,
                super.description,
                this.startTime,
                this.endTime
        );
    }

    @Override
    public String toString() {
        return String.format(
                "[E][%s] %s (from: %s to: %s)",
                super.getStatusIcon(),
                super.description,
                this.startTime,
                this.endTime
        );
    }
}
