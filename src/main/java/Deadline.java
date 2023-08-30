import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;
    private static final DateTimeFormatter DATE_INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");


    /**
     * Constructor to create a Deadline object.
     *
     * @param description The task description.
     * @param by The deadline of the task in the format dd/MM/yyyy.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, DATE_INPUT_FORMATTER);
    }

    /**
     * Method to get the string representation of the deadline task.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DATE_OUTPUT_FORMATTER) + ")";
    }

    /**
     * Returns deadline task information in format for saving.
     * Format is D | [1 if completed, 0 if not completed] | [task description] | [by]
     *
     * @return Deadline task information in format for saving
     */
    @Override
    public String getInformationForSaving() {
        return "D | " + super.getInformationForSaving() + " | " + this.by.format(DATE_INPUT_FORMATTER);
    }
}
