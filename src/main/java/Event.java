import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    private static final DateTimeFormatter DATETIME_INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter DATETIME_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    /**
     * Constructor to create an Event object.
     *
     * @param description The task description.
     * @param from The task's start date/time in the format dd/MM/yyyy HHmm.
     * @param to The task's end date/time in the format.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DATETIME_INPUT_FORMATTER);
        this.to = LocalDateTime.parse(to, DATETIME_INPUT_FORMATTER);
    }

    /**
     * Returns a boolean representing whether the event starts within a week.
     *
     * @return true if the event starts within a week, false otherwise
     */
    @Override
    public boolean isWithinAWeek() {
        return this.from.isBefore(LocalDateTime.now().plus(1, ChronoUnit.WEEKS));
    }

    /**
     * Method to get the string representation of the event task.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return String.format(
                "[E]%1s (from: %2s to: %3s)",
                super.toString(),
                this.from.format(DATETIME_OUTPUT_FORMATTER),
                this.to.format(DATETIME_OUTPUT_FORMATTER)
        );
    }

    /**
     * Returns deadline task information in format for saving.
     * Format is E | [1 if completed, 0 if not completed] | [task description] | [from] | [to]
     *
     * @return Deadline task information in format for saving
     */
    @Override
    public String getInformationForSaving() {
        return String.format(
                "E | %1s | %2s | %3s",
                super.getInformationForSaving(),
                this.from.format(DATETIME_INPUT_FORMATTER),
                this.to.format(DATETIME_INPUT_FORMATTER));
    }
}
