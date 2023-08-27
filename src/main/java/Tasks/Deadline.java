package Tasks;

import Utilities.Exceptions.IncompleteDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private String deadline;
    private LocalDate ld;

    public Deadline(String taskName, String deadline) throws IncompleteDescriptionException {
        super(taskName);
        try {
            this.ld = LocalDate.parse(deadline);
        } catch (DateTimeParseException ignored) {}
        this.deadline = deadline;
    }

    public static Deadline create(String taskDesc) throws IncompleteDescriptionException {
        String[] tmp = taskDesc.split(" /by ");
        if (tmp.length <= 1) throw new IncompleteDescriptionException();
        String taskName = tmp[0];
        String deadline = tmp[1];
        if (taskName.isBlank() || deadline.isBlank()) throw new IncompleteDescriptionException();
        return new Deadline(tmp[0], tmp[1]);
    }

    @Override
    public String compressData() {
        char isDoneChar = this.isDone ? '1' : '0';
        return "D" + " | " + isDoneChar + " | " + this.taskName + " | " + this.deadline;
    }

    @Override
    public String toString() {
        if (this.ld != null) {
            return "[D]" + super.toString() + " (by: " + ld.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}