package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Events extends duke.tasks.Task {
    // additional start and end time fields for events
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * The event constructor.
     *
     * @param description The string with description of task.
     * @param startDate The string with start date.
     * @param endDate The string with end date.
     */
    public Events(String description, String startDate, String endDate) {
        super(description, 'T');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime startDateParsed = LocalDateTime.parse(startDate.trim(), formatter);
        LocalDateTime endDateParsed = LocalDateTime.parse(endDate.trim(), formatter);
        this.startDate = startDateParsed;
        this.endDate = endDateParsed;

    }

    /**
     * Getter for the start date.
     *
     * @return the start date.
     */
    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    /**
     * Getter for the end date.
     *
     * @return the end date.
     */
    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    /**
     * Converts the Start date from numbers to words.
     *
     * @return The converted deadline.
     */
    public String getStartDateInWords() {
        String dayOfWeek = startDate.getDayOfWeek().toString();
        int dayOfMonth = startDate.getDayOfMonth();
        String month = startDate.getMonth().toString();
        int year = startDate.getYear();
        return dayOfWeek + " " + dayOfMonth + " " + month + " " + year;
    }

    /**
     * Converts the end date from numbers to words.
     *
     * @return The converted deadline.
     */
    public String getEndDateInWords() {
        String dayOfWeek = endDate.getDayOfWeek().toString();
        int dayOfMonth = endDate.getDayOfMonth();
        String month = endDate.getMonth().toString();
        int year = endDate.getYear();
        return dayOfWeek + " " + dayOfMonth + " " + month + " " + year;
    }

    @Override
    public String toString() {
        return String.format("[E] %s + (from: %s  to: %s", super.toString(),
                getStartDateInWords(), getEndDateInWords());
    }
}
