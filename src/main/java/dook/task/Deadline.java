package dook.task;

import dook.services.TimeProcessor;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task implements TimedTask {
    protected String by;
    protected LocalDate byDateTime;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        processDateTimes();
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        processDateTimes();
    }
    @Override
    public void processDateTimes() {
        try {
            byDateTime = TimeProcessor.getLocalDateFromString(this.by);
            this.by = TimeProcessor.getStringFromLocalDate(byDateTime);
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
        return String.format("D//%s//%s//%s", getStatusIcon(), description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}