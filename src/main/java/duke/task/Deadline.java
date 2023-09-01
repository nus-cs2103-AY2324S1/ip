package duke.task;

import java.time.LocalDateTime;

import duke.parser.TimeParser;

public class Deadline extends Task {
    private LocalDateTime date;
    public Deadline(String name, LocalDateTime date) {
        this.name = name;
        this.date = date;
        this.status = false;
        this.type = TaskTypes.DEADLINE;
    }

    public Deadline(String name, boolean status, LocalDateTime date) {
        this.name = name;
        this.date = date;
        this.status = status;
        this.type = TaskTypes.DEADLINE;
    }

    @Override
    public String toString() {
        String statusMark = this.status ? "[✓]" : "[✕]";
        return String.format("[D]%s %s (by: %s)", statusMark, name, TimeParser.returnTime(date));
    }

    @Override
    public String toSave() {
        return String.format("D%s%s%s%d%s%s", DISCRIMINATOR, name, DISCRIMINATOR,
                Boolean.compare(status, false), DISCRIMINATOR, TimeParser.toSaveString(date));
    }
}
