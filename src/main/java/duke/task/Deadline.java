package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    private String deadlineBy;

    private LocalDate deadlineDate;

    private LocalDateTime deadlineDateTime;

    /**
     * Constructs a Deadline object with the task description and deadline.
     *
     * @param description Description of the task.
     * @param deadlineBy Deadline of the task.
     */
    public Deadline(String description, String deadlineBy) {
        super(description);
        this.deadlineDate = parseDate(deadlineBy);
        this.deadlineDateTime = parseDateTime(deadlineBy);
        this.deadlineBy = deadlineBy;
    }

    /**
     * Formats the string representation of deadline object to write to the file.
     *
     * @return String representation of the deadline object to be written to the file.
     */
    @Override
    public String toFileString() {
        String type = "D";
        return type + " | " + (getIsDone() ? "1" : "0") + " | " + this.description + " | " + this.deadlineBy;
    }

    /**
     * The string representation of deadline object.
     *
     * @return String representation of the deadline object.
     */
    @Override
    public String toString() {
        if (deadlineDate != null) {
            String formattedDate = deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            return String.format("[D]%s (by: %s)", super.toString(), formattedDate);
        } else if (deadlineDateTime != null) {
            String formattedDate = deadlineDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
            return String.format("[D]%s (by: %s)", super.toString(), formattedDate);
        } else {
            return String.format("[D]%s (by: %s)", super.toString(), deadlineBy);
        }

    }

    /**
     * Parses a String to a LocalDate object
     *
     * @param date String representation of potential date
     * @return A LocalDate object if string can be parsed, null otherwise
     */
    private LocalDate parseDate(String date) {

        List<DateTimeFormatter> formatters = new ArrayList<>();
        //List of accepted data formats
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        formatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        formatters.add(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy"));

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException ignore){

            }
        }

        return null;

    }

    /**
     * Parses a String to a LocalDateTime object
     *
     * @param date String representation of potential date and time
     * @return A LocalDateTime object if string can be parsed, null otherwise
     */
    private LocalDateTime parseDateTime(String date) {

        List<DateTimeFormatter> formatters = new ArrayList<>();
        //List of accepted data formats
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")); //format from reading file
        formatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        formatters.add(DateTimeFormatter.ofPattern("MM/dd/yyyy HHmm"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(date, formatter);
            } catch (DateTimeParseException ignore) {

            }
        }
        return null;

    }
}