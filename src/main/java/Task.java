import java.io.Serializable;
import java.security.InvalidParameterException;
import java.time.LocalDate;
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
     * Parses date string into LocalDate objects, with dateString in the format yyyy-mm-dd
     *
     * @param dateString String format of the date parsed in yyyy-mm-dd
     */
    protected LocalDate parseDates(String dateString) throws InvalidParameterException {
        try {
            LocalDate date = LocalDate.parse(dateString);
            return date;
        } catch (Exception e) {
            System.out.println("Wrong date format provided");
            throw new InvalidParameterException();
        }
    }

    /**
     * Returns the string format of date objects
     * @param date Date to be convereted to a string
     * @return String format of the date in MMM dd yyyy format
     */
    protected String printDates(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

}