import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event (String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static Event EventCon(String description, String from, String to) throws InvalidTaskCreationException, DateTimeParseException {
        if (description.equalsIgnoreCase("")) {
            throw new InvalidTaskCreationException("OOPS!!! The description of a Event Task cannot be empty.");
        } else if (from.equalsIgnoreCase("")) {
            throw new InvalidTaskCreationException("OOPS!!! The from time of a Event Task cannot be empty.");
        } else if (to.equalsIgnoreCase("")) {
            throw new InvalidTaskCreationException("OOPS!!! The to time of a Event Task cannot be empty.");
        } else {

            LocalDateTime eventStartTimeDate = LocalDateTime.parse(from, Task.DTformatter);
            LocalDateTime eventEndTimeDate = LocalDateTime.parse(to, Task.DTformatter);
            return new Event(description, eventStartTimeDate, eventEndTimeDate);
        }
    }

    public LocalDateTime getUrgencyDate() {
        return this.from;
    }

    @Override
    public String toString() {
        String formattedDateFrom = this.from.format(Task.outputFormatter);
        String formattedDateTo = this.to.format(Task.outputFormatter);
        return "[E]" + super.toString() + " (from: " + formattedDateFrom + " to: " + formattedDateTo + ")";
    }
}
