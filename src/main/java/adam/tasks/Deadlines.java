package adam.tasks;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class is used to create Deadline objects that holds the description and when the task is due
 */
public class Deadlines extends Task implements Serializable {
    /**
     * This variable holds the information on when this instance of a deadline object is due
     */
    protected LocalDate by;

    /**
     * This is a constructor that creates a Deadline object
     * @param text This param gives the description of the task
     * @param by This param tells you when this task is due
     */
    public Deadlines(String text, String by){
        super(text);
        this.by = LocalDate.parse(by);
    }

    /**
     * This is a toString method that has been Override to better suit the display of this class
     * @return The string form and information about this deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()  + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
