package brotherman.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a deadline task
 */
public class Deadline extends Task{

    /**
     * Time of the deadline
     */
    private String time;

    /**
     * Time of the deadline
     */
    private LocalDateTime timeInDateTime;


    /**
     * Constructor for Deadline
     * @param description Description of the deadline
     * @param time Time of the deadline
     */
    public Deadline(String description, LocalDateTime timeInDateTime) {

        super(description);
        this.timeInDateTime = timeInDateTime;
    }

    /**
     * Returns the type of the task
     * @return Type of the task
     */
    public String type() {
        return "D";
    }


    /**
     * Returns the time of the deadline
     * @return Time of the deadline
     */
    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = timeInDateTime.format(formatter);
        return text;
    }


    /**
     * Returns the time of the deadline
     * @return Time of the deadline
     */
    @Override
    public String storeText() {
        return String.format("%s|%s|%s/by%s", this.type(), this.isDone, this.description, getTime());
    }


    /**
     * Returns the string representation of the deadline
     * @return String representation of the deadline
     */
    @Override
    public String toString() {
        String type = this.type();
        return String.format("[%s]%s(by:%s)", type, super.toString(), getTime() );
    }

}
