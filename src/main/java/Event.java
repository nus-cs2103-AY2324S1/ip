import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate e_start;
    protected LocalDate e_end;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Event(String description, String e_start, String e_end) throws DukeException {
        super(description, TaskType.EVENT);
        try {
            this.e_start = LocalDate.parse(e_start, FORMATTER);
            this.e_end = LocalDate.parse(e_end, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid time format.");
        }
    }

    public Event(String description, String time, Boolean isDone) throws DukeException {
        super(description, TaskType.EVENT, isDone);
        try {
        String[] splits = time.split("-");
        this.e_start = LocalDate.parse(splits[0].trim(), FORMATTER);
        this.e_end = LocalDate.parse(splits[1].trim(), FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid time format.");
        }
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + e_start.format(OUTPUT_FORMATTER)
                + " to: " + e_end.format(OUTPUT_FORMATTER) + ")";
    }
}