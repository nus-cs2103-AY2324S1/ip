package tasks;
import java.time.DateTimeException;
import java.time.LocalDateTime;

import exceptions.DukeException;

public class EventTask extends Task {

    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    public EventTask(String description, String startDate, String endDate) throws DukeException {
        super(description);
        try {
            this.startDate = LocalDateTime.parse(startDate, Task.getDateFormat());
            this.endDate = LocalDateTime.parse(endDate, Task.getDateFormat());
        } catch (DateTimeException e) {
            throw new DukeException("Date should follow the format d/M/yyyy HHmm");
        }
    }

    public EventTask() {
        super("");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate.format(Task.getDateFormatOutput())
                + " to: " + endDate.format(Task.getDateFormatOutput()) + ")";
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public String toFileString() {
        return "E | " + super.getStatusIcon() + " | " + getDescription() + " | "
                + startDate.format(Task.getDateFormat()) + " | " + endDate.format(Task.getDateFormat());
    }

    public void fromFileString(String fileString) throws DukeException {
        String[] fileStringArray = fileString.split(" \\| ");
        this.setStatusIcon(fileStringArray[1]);
        this.setDescription(fileStringArray[2]);
        try {
            this.startDate = LocalDateTime.parse(fileStringArray[3], Task.getDateFormat());
            this.endDate = LocalDateTime.parse(fileStringArray[4], Task.getDateFormat());
        } catch (DateTimeException e) {
            throw new DukeException("Date should follow the format d/M/yyyy HHmm");
        }
    }
}
