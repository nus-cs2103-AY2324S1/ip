package duke.task;
import duke.exception.DukeException;
import duke.exception.InvalidTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate deadline;
    public Deadline(String description) throws DukeException {
        super(description.substring(0, description.indexOf("/by") - 1));
        int byIndex = description.indexOf("/by");
        try{
            this.deadline = LocalDate.parse(description.substring(byIndex + 4));
        }
        catch (DateTimeParseException e) {
            throw new InvalidTimeException("Invalid input of Date");
        }
    }
    @Override
    public String getInput() {
        return "D | " + this.getStatusIcon() + " | " + this.description + " | " + this.deadline;
    }
    @Override
    public String toString() {
        return "[D] [" + this.getStatusIcon() + "] " + this.description + " (by: " +
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
