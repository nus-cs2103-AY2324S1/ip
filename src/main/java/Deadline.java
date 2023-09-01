import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Deadline extends Task{
    private final String descr;

    public Deadline(String descr) {
        super(descr.split("/by")[0]);
        this.descr = descr;
    }

    public void checkValidity() throws DukeException {
        String[] descrArr = descr.split("/"); //you get 0: taskName, 1: deadline
        if (descrArr.length < 2) {
            throw new DukeException("You are missing the deadline");
        }
    }

    public String writtenFormat() {
        String[] parts = this.descr.split("/by");
        String eventType = "deadline";
        String eventDescription = parts[0].substring(eventType.length()).trim();
        return "D | " + super.status() + "| " + eventDescription + " | " + parts[1];
     }

    @Override
    public String toString() {
        String deadline = this.descr.split("/by")[1].trim();
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
