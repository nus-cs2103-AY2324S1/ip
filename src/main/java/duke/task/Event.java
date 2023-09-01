package duke.task;

import java.time.LocalDateTime;

import duke.parser.TimeParser;

public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    public Event(String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.status = false;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = TaskTypes.EVENT;
    }

    public Event(String name, boolean status, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = TaskTypes.EVENT;
    }

    @Override
    public String toString() {
        String statusMark = this.status ? "[✓]" : "[✕]";
        return String.format("[E]%s %s (from: %s to: %s)", statusMark, name,
                TimeParser.returnTime(startDate), TimeParser.returnTime(endDate));
    }

    @Override
    public String toSave() {
        return String.format("E%s%s%s%d%s%s to %s", DISCRIMINATOR, name, DISCRIMINATOR,
                Boolean.compare(status, false), DISCRIMINATOR, TimeParser.toSaveString(startDate),
                TimeParser.toSaveString(endDate));
    }
}
