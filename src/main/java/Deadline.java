import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String deadlineString;
    private LocalDate deadlineDate;

    public Deadline (String name, String deadlineString) {
        super(name);
        this.deadlineString = deadlineString;
        this.deadlineDate = parseDateTime(deadlineString);
    }

    public String getDeadlineString() {
        return this.deadlineString;
    }

    public String writeTaskToFile() {
        return String.format("%s | %s | %s | %s", "D",
                this.isDone() ? 1 : 0,
                this.getName(),
                this.getDeadlineString());
    }

    public static Deadline readTaskFromFile(String[] args) {
        Deadline newDeadlineTask = new Deadline(args[2], args[3]);
        if (args[1].equals("1")) {
            newDeadlineTask.markDone();
        }
        return newDeadlineTask;
    }

    private LocalDate parseDateTime(String deadlineString) {
        try {
            return LocalDate.parse(deadlineString);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        String deadline;
        if (deadlineDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            deadline = deadlineDate.format(formatter);
        }
        else {
            deadline = deadlineString;
        }
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
