package Tasks;
import Exceptions.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline. A <code>Deadline</code> object
 * has a <code>LocalDate</code> deadline.
 */
public class Deadline extends Task {
    private LocalDate deadline;
    public Deadline(String description) throws DukeException {
        // TODO: Date.now() when "today" is entered
        super(description);
        // Split the input string by "/by" to separate description and deadline
        String[] splitString = description.split("/by", 2);
        // Should throw error if there are multiple "/by" or no "/by"
        if (splitString.length >=2) {
            this.description = splitString[0].trim();
            String deadlineString = splitString[1].trim();
            this.deadline = super.parseDate(deadlineString);
        }
    }

    public Deadline(String description, String deadlineString) {
        super(description);
        // deadline will be in default yyyy-MM-dd format which can be parsed by LocalDate
        this.deadline = LocalDate.parse(deadlineString);
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    @Override
    public String toString() {
        if (deadline == null) return super.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        return super.toString() + String.format(" (by: %s)",deadline.format(formatter));
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + (deadline == null ? "" : " | " + deadline.toString());
    }
}
