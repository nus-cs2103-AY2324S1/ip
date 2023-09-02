package chatbuddy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    private static final DateTimeFormatter FORMATTER_DATE_INPUT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATTER_DATE_OUTPUT = DateTimeFormatter.ofPattern("dd MMM yyyy");
    protected LocalDate by;


    /**
     * Constructor to create a chatbuddy.task.Deadline object.
     *
     * @param description The task description.
     * @param by The deadline of the task in the format dd/MM/yyyy.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a boolean representing whether the task is due within a week.
     *
     * @return true if the deadline is due within a week, false otherwise
     */
    @Override
    public boolean isWithinAWeek() {
        return by.isBefore(LocalDate.now().plus(1, ChronoUnit.WEEKS));
    }

    /**
     * Method to get the string representation of the deadline task.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(FORMATTER_DATE_OUTPUT) + ")";
    }

    /**
     * Returns deadline task information in format for saving.
     * Format is D | [1 if completed, 0 if not completed] | [task description] | [by]
     *
     * @return chatbuddy.task.Deadline task information in format for saving
     */
    @Override
    public String getInformationForSaving() {
        return "D | " + super.getInformationForSaving() + " | " + by.format(FORMATTER_DATE_INPUT);
    }
}
