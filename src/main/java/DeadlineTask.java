import java.time.DateTimeException;
import java.time.LocalDateTime;

public class DeadlineTask extends Task {

    protected LocalDateTime by;

    public DeadlineTask(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, Task.DATE_FORMAT);
        } catch (DateTimeException e) {
            throw new DukeException("Date should follow the format d/M/yyyy HHmm");
        }
    }

    public DeadlineTask() {
        super("");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DATE_FORMAT_OUTPUT) + ")";
    }

    public String toFileString() {
        return "D | " + super.getStatusIcon() + " | " + description + " | " + by.format(DATE_FORMAT);
    }

    public void fromFileString(String fileString) throws DukeException {
        String[] fileStringArray = fileString.split(" \\| ");
        this.setStatusIcon(fileStringArray[1]);
        this.description = fileStringArray[2];
        try {
            this.by = LocalDateTime.parse(fileStringArray[3], Task.DATE_FORMAT);
        } catch (DateTimeException e) {
            throw new DukeException("Date should follow the format d/M/yyyy HHmm");
        }
    }

}
