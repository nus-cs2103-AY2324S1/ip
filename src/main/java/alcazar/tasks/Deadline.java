package alcazar.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a deadline Task
 */
public class Deadline extends Task {

    /** The deadline completion date in MMM dd yyyy format */
    private String formattedByDate;

    /**
     * Constructs a new Deadline
     * @param description The description of this deadline
     * @param inputByDate The input completion date for this deadline
     */
    public Deadline(String description, String inputByDate) {
        super(description);
        this.formattedByDate = parseDate(inputByDate.trim());

    }

    /**
     * Parses the input date into a MMM dd yyyy format
     * @param inputByDate The input date, potentially in a different format
     * @return String representation of the formatted date
     */
    public String parseDate(String inputByDate) {
        String dateAfterFormatting = "";
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
            LocalDate date = LocalDate.parse(inputByDate, inputFormat);
            dateAfterFormatting = date.format(outputFormat);
        } catch (DateTimeParseException e) {
            dateAfterFormatting = inputByDate;
        }
        return dateAfterFormatting;

    }
    /**
     * Evaluates String form of a Deadline
     * @return String form of a Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + formattedByDate + ")";
    }
}
