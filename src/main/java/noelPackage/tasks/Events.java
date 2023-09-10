package noelPackage.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task
 */
public class Events extends Task {

    final String TASK_CHAR = "[E]";
    protected LocalDate startDate;
    protected LocalTime startTime;
    protected LocalDate endDate;
    protected LocalTime endTime;
    protected String dateString = "";

    /**
     * Creates a new Event task with given details.
     *
     * @param taskName The name of the event.
     * @param startDate The starting date of the event.
     * @param startTime The starting time of the event.
     * @param endDate The ending date of the event.
     * @param endTime The ending time of the event.
     */
    public Events(String taskName, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        super(taskName);
        setDates(startDate, startTime, endDate, endTime);
    }

    /**
     * Helper method for the constructor method
     * @param startDate The starting date of the event.
     * @param startTime The starting time of the event.
     * @param endDate The ending date of the event.
     * @param endTime The ending time of the event.
     */
    public void setDates(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        String startDateTime = formatDate(this.startDate) + " " + formatTime(this.startTime);
        String endDateTime = formatDate(this.endDate) + " " + formatTime(this.endTime);
        this.dateString = "(from: " + startDateTime + " to: " + endDateTime + ")";
    }

    /**
     * Formats date to String
     * @param date takes in date of LocalDate type
     * @return String of date formatted as yyyy-MM-dd
     */
    public String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Formats time to String
     * @param time takes in time of LocalTime type
     * @return String of time formatted as HH:mm
     */
    public String formatTime(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String toFileString() {
        return "E | " + super.getStatusNumber() + " | " + this.taskName + " | " + this.dateString;
    }

    @Override
    public String toString() {
        return TASK_CHAR + super.toString() + this.dateString;
    }
}
