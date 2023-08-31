package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Deadline extends Task {

    private String deadlineBy;

    private LocalDate deadlineDate;

    private LocalDateTime deadlineDateTime;

    public Deadline(String description, String deadlineBy) {
        super(description);
        this.deadlineDate = parseDate(deadlineBy);
        this.deadlineDateTime = parseDateTime(deadlineBy);
        this.deadlineBy = deadlineBy;
    }

    @Override
    public String toFileString() {
        String type = "D";
        return type + " | " + (isDone() ? "1" : "0") + " | " + this.description + " | " + this.deadlineBy;
    }


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

    //parse LocalDate Object
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

    //parse LocalDateTime Object
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