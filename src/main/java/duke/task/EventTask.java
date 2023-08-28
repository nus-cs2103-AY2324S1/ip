package duke.task;

import duke.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    protected LocalDate fromDate;
    protected LocalDate toDate;

    public EventTask(String description, String from, String to, boolean isDone) throws DukeException {
        super(description, isDone);
        try {
            setEventFromDate(from);
            setEventToDate(to);
        } catch (DateTimeException e) {
            throw new DukeException("Please input your date in the YYYY-MM-DD format!");
        }
    }

    public void setEventFromDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.fromDate = LocalDate.parse(date, formatter);
    }

    public void setEventToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.toDate = LocalDate.parse(date, formatter);
    }

    public String getFormattedFromDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return fromDate.format(formatter);
    }

    public String getFormattedToDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return toDate.format(formatter);
    }

    public String toSave() {
        return "[E]" + super.toString() + " (from: " + this.fromDate + " to: " + this.toDate + ")";
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFormattedFromDate() + " to: " + getFormattedToDate() + ")";
    }
}
