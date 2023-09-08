import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private LocalDate day;


    private Deadline(String description, LocalDate day) {
        super(description);
        this.day = day;
    }

    public Deadline(String description, LocalDate day, boolean isDone) {
        super(description);
        this.day = day;
        this.isDone = isDone;
    }

    public static Deadline createNewDeadlineTask(String description) throws DateTimeParseException{
        String[] splitMessage = description.split(" /by ");
        LocalDate date = LocalDate.parse(splitMessage[1]);
        return new Deadline(splitMessage[0], date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.day.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "[D]" + super.toString() + " (by: " + this.day + ")";
    }
}
