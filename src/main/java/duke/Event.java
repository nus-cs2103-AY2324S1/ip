package duke;

import duke.Parser;
import duke.Task;
import duke.exception.DetailsUnknownException;

import java.time.LocalDateTime;

public class Event extends Task {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Event(String task, String startDetails, String endDetails) {
        super(task);
        this.startDateTime = Parser.formatDate(startDetails);
        this.endDateTime = Parser.formatDate(endDetails);
    }

    public Event(String task, String dateTimeDetails) throws DetailsUnknownException {
        super(task);
        try {
            String[] timeArr = Parser.splitEventDateTime(dateTimeDetails); // return [start time, end time]
            this.startDateTime = Parser.formatDate(timeArr[0]);
            this.endDateTime = Parser.formatDate(timeArr[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DetailsUnknownException();
        }
    }

    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return this.endDateTime;
    }


    @Override
    public String toString() {
        String formattedStart = Parser.dateToString(startDateTime);
        String formattedEnd = Parser.dateToString(endDateTime);
        return "[E]" + super.toString() + " (from: " + formattedStart + " to: " + formattedEnd + ") ";
    }
}