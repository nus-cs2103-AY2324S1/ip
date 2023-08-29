package pooh;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final String deadlineString;
    private final LocalDate deadlineDateTime;

    public Deadline(String description, String deadlineString) {
        super(description);
        this.deadlineString = deadlineString;
        this.deadlineDateTime = parseDateTime(deadlineString);

    }

    public String writeTaskToFile() {
        return String.format("%s | %s | %s | %s", "D",
                this.getIsDone() ? 1 : 0,
                this.getDescription(),
                this.deadlineString);
    }

    public static Deadline readTaskFromFile(String[] args) {
        Deadline newDeadlineTask = new Deadline(args[2], args[3]);
        if (args[1].equals("1")) {
            newDeadlineTask.markAsDone();
        }
        return newDeadlineTask;
    }

    private LocalDate parseDateTime(String deadlineString) {
        try {
            return LocalDate.parse(deadlineString);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    @Override
    public String toString() {
        String deadline;
        if (deadlineDateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            deadline = deadlineDateTime.format(formatter);
        } else {
            deadline = deadlineString;
        }
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
