package dook.task;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import dook.services.TimeProcessor;

/**
 * Task with a start and end date.
 */
public class Event extends Task implements TimedTask {

    protected String fromString;
    protected String toString;
    protected LocalDate fromDateTime = null;
    protected LocalDate toDateTime = null;

    /**
     * Instantiates a event with given description, timeframe and done status.
     * @param description Given description
     * @param fromString Given start date string
     * @param toString Given end date string
     * @param isDone Given done status.
     */
    public Event(String description, String fromString, String toString, boolean isDone) {
        super(description, isDone);
        this.fromString = fromString;
        this.toString = toString;
        processDateTimes();
    }

    @Override
    public void processDateTimes() {
        try {
            fromDateTime = TimeProcessor.getLocalDateFromString(this.fromString);
            toDateTime = TimeProcessor.getLocalDateFromString(this.toString);
            this.fromString = TimeProcessor.getStringFromLocalDate(fromDateTime);
            this.toString = TimeProcessor.getStringFromLocalDate(toDateTime);
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse date. Date related operations will not work on this task.");
        }
    }
    @Override
    public boolean isDuring(LocalDate dateTime) {
        try {
            boolean isDateAfterStart = fromDateTime.isEqual(dateTime) || fromDateTime.isBefore(dateTime);
            boolean isDateBeforeEnd = toDateTime.isEqual(dateTime) || toDateTime.isAfter(dateTime);
            return isDateBeforeEnd && isDateAfterStart;
        } catch (NullPointerException e) {
            return false;
        }
    }
    @Override
    public boolean isAfter(LocalDate dateTime) {
        try {
            return fromDateTime.isAfter(dateTime);
        } catch (NullPointerException e) {
            return false;
        }
    }
    @Override
    public boolean isBefore(LocalDate dateTime) {
        try {
            return toDateTime.isBefore(dateTime);
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public String getSaveableString() {
        return String.format("E//%s//%s//%s//%s", getStatusIcon(), description, fromString, toString);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromString + " to: " + toString + ")";
    }
}
