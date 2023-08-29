package duke.task;

import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime time;

    public Deadline(String name, String time) {
        super(name);
        this.time = timeConverter(time);
    }

    public LocalDateTime timeConverter(String time) throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (!time.contains(" ")) {
            time += " 2359";
        }
        return LocalDateTime.parse(time, formatter);
    }

    @Override
    public String toSave() {
        String timeToSave = time.toString().replace("T", " ").replace(":","");
        return (super.isComplete ? "1 " : "0 ")  + "deadline " + super.name + "/by " + timeToSave;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "(by: " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}
