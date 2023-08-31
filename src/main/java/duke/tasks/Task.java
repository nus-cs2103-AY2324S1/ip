package duke.tasks;

import java.io.Serializable;

import java.security.InvalidParameterException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Superclass that supports task methods
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task invoked by superclass
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * If tasks is done indicates "X" else shows a blank
     * @return returns a string of either X or blank
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone
     */
    public void unmarkAsDone() { this.isDone = false; }

    /**
     * String representation of the task
     * @return details of the task
     */
    @Override
    public String toString() {
        String ret = "[" + getStatusIcon() + "] " + description;

        return ret;
    }

    /**
     * Parses date string into LocalDate objects, with dateString in the format yyyy-mm-dd HHmm
     *
     * @param datetimeString String format of the date and or time parsed in yyyy-mm-dd HHmm
     */
    protected LocalDateTime parseDateTime(String datetimeString) throws InvalidParameterException {
        try {
            String pattern = "yyyy-MM-dd HHmm";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime datetime = LocalDateTime.parse(datetimeString, formatter);
            return datetime;
        } catch (Exception e) {
            System.out.println("Wrong date format provided");
            throw new InvalidParameterException();
        }
    }

    /**
     * Returns the string format of datetime objects
     * @param date Date to be converted to a string
     * @return String format of the date in MMM dd yyyy HHmm format
     */
    protected String printDateTime(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task
     *
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }
}