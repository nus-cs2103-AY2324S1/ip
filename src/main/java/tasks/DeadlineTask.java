package tasks;
import java.time.DateTimeException;
import java.time.LocalDateTime;

import exceptions.DukeException;

public class DeadlineTask extends Task {

    protected LocalDateTime by;

    public DeadlineTask(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, Task.getDateFormat());
        } catch (DateTimeException e) {
            throw new DukeException("Date should follow the format d/M/yyyy HHmm");
        }
    }

    public DeadlineTask() {
        super("");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(Task.getDateFormatOutput()) + ")";
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    public String toFileString() {
        return "D | " + super.getStatusIcon() + " | " + getDescription() + " | " + by.format(Task.getDateFormat());
    }

    public void fromFileString(String fileString) throws DukeException {
        String[] fileStringArray = fileString.split(" \\| ");
        this.setStatusIcon(fileStringArray[1]);
        this.setDescription(fileStringArray[2]);
        try {
            this.by = LocalDateTime.parse(fileStringArray[3], Task.getDateFormat());
        } catch (DateTimeException e) {
            throw new DukeException("Date should follow the format d/M/yyyy HHmm");
        }
    }

}
