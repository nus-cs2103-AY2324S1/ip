package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Event extends Task {
    private String startDateTimeStr;
    private String endDateTimeStr;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;


    public Event(String description, String startDateTimeStr,String endDateTimeStr) {
        super(description);
        this.startDate = parseDate(startDateTimeStr);
        this.endDate = parseDate(endDateTimeStr);
        this.startDateTime = parseDateTime(startDateTimeStr);
        this.endDateTime = parseDateTime(endDateTimeStr);
        this.startDateTimeStr = startDateTimeStr;
        this.endDateTimeStr = endDateTimeStr;

    }

    @Override
    public String toFileString() {
        String type = "E";
        return type + " | " + (isDone() ? "1" : "0") + " | " + this.description + " | "
                + this.startDateTimeStr + " to " + this.endDateTimeStr;
    }

    @Override
    public String toString() {

        String startStr = startDateTimeStr;
        String endStr = endDateTimeStr;

        if (startDateTime != null) {
            startStr = startDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        } else if (startDate != null) {
            startStr = startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }

        if (endDateTime != null) {
            endStr = endDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        } else if (endDate != null) {
            endStr = endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }

        return String.format("[E]%s (from: %s to %s)", super.toString(), startStr, endStr);
    }

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
