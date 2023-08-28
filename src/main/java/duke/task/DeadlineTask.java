package duke.task;

import duke.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    protected LocalDate byDate;
    public DeadlineTask(String description, String byDate, boolean isDone) throws DukeException {
        super(description, isDone);
        try {
            setDeadlineByDate(byDate);
        } catch (DateTimeException e) {
            throw new DukeException("Please input your date in the YYYY-MM-DD format!");
        }
    }

    public void setDeadlineByDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.byDate = LocalDate.parse(date, formatter);
        } catch (DateTimeException e) {
            throw new DukeException("There is no such data!");
        }
    }

    public String getFormattedDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return byDate.format(formatter);
    }

    public LocalDate getByDate() {
        return byDate;
    }

    public String toSave() {
        return "[D]" + super.toString() + " (by: " + this.byDate + ")";
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedDeadline() + ")";
    }
}
