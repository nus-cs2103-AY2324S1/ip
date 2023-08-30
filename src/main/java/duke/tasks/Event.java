package duke.tasks;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String start;
    protected String end;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");

    public Event(String description, String startTime, String endTime) throws DukeException {
        super(description);

        try {
            LocalDateTime parseStart = LocalDateTime.parse(startTime, inputFormatter);
            LocalDateTime parseEnd = LocalDateTime.parse(endTime, inputFormatter);
            start = parseStart.format(outputFormatter);
            end = parseEnd.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("duke.tasks.Event times must be in this format: yyyy-mm-dd HH:mm");
        }

    }

    public String exportData() {
        return "E | " + this.getStatusIcon() + " | " + this.description + " | " + this.start + "-" + this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
