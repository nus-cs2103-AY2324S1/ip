package duke.task;

import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Event (String name, String start, String end) {
        super(name);
        this.start = timeConverterStart(start);
        this.end = timeConverterEnd(end);
    }

    public LocalDateTime timeConverterStart(String time) throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (!time.contains(" ")) {
            time += " 0000";
        }
        return LocalDateTime.parse(time, formatter);
    }

    public LocalDateTime timeConverterEnd(String time) throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (!time.contains(" ")) {
            time += " 2359";
        }
        return LocalDateTime.parse(time, formatter);
    }

    @Override
    public String toSave() {
        String startToSave = start.toString().replace("T", " ").replace(":", "");
        String endToSave = end.toString().replace("T", " ").replace(":", "");
        return (super.isComplete ? "1 " : "0 ") + "event " + super.name + "/from " + startToSave + " /to " + endToSave;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "/from " + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                        + " /to " + end.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }
}
