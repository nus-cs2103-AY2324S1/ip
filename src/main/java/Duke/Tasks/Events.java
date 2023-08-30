package Duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Duke.Tasks.Task {
    // additional start and end time fields for events
    LocalDateTime startDate;
    LocalDateTime endDate;
    public Events(String description, String startDate, String endDate) {
        super(description, 'T');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime startDateParsed = LocalDateTime.parse(startDate.trim(), formatter);
        LocalDateTime endDateParsed = LocalDateTime.parse(endDate.trim(), formatter);
        this.startDate = startDateParsed;
        this.endDate = endDateParsed;

    }

    // getters for start and end times
    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public String getStartDateInWords() {
        String dayOfWeek = startDate.getDayOfWeek().toString();
        int dayOfMonth = startDate.getDayOfMonth();
        String month = startDate.getMonth().toString();
        int year = startDate.getYear();
        return dayOfWeek + " " + dayOfMonth + " " + month + " " + year;
    }

    public String getEndDateInWords() {
        String dayOfWeek = endDate.getDayOfWeek().toString();
        int dayOfMonth = endDate.getDayOfMonth();
        String month = endDate.getMonth().toString();
        int year = endDate.getYear();
        return dayOfWeek + " " + dayOfMonth + " " + month + " " + year;
    }

    @Override
    public String toString() {
        return String.format("[E] %s + (from: %s  to: %s",super.toString(),getStartDateInWords(), getEndDateInWords());
    }
}
