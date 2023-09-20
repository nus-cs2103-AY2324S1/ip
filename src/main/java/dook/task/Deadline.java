package dook.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import dook.services.TimeProcessor;

/**
 * Task with a deadline.
 */
public class Deadline extends Task implements TimedTask {
    protected String byString;
    protected LocalDate byDateTime;

    /**
     * Instantiates a deadline with given description, deadline and done status.
     * @param description Given description
     * @param by Given deadline string
     * @param isDone Given done status.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.byString = by;
        processDateTimes();
    }
    @Override
    public void processDateTimes() {
        try {
            byDateTime = TimeProcessor.getLocalDateFromString(this.byString);
            this.byString = TimeProcessor.getStringFromLocalDate(byDateTime);
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse date. Date related operations will not work on this task.");
        }
    }
    @Override
    public boolean isDuring(LocalDate dateTime) {
        try {
            return byDateTime.isEqual(dateTime);
        } catch (NullPointerException e) {
            return false;
        }
    }
    @Override
    public boolean isAfter(LocalDate dateTime) {
        try {
            return byDateTime.isAfter(dateTime);
        } catch (NullPointerException e) {
            return false;
        }
    }
    @Override
    public boolean isBefore(LocalDate dateTime) {
        try {
            return byDateTime.isBefore(dateTime);
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public String getSaveableString() {
        return String.format("D//%s//%s//%s", getStatusIcon(), description, byString);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byString + ")";
    }
}
