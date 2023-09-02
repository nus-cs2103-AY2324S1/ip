package chatbot.tasks;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline
 */
public class Deadline extends Task {
    /**
     * Due date of the deadline
     */
    protected String date;
    /**
     * Formatted due date of the deadline
     */
    protected LocalDateTime formattedDate;

    /**
     * Constructor for Deadline
     * @param description Description of the deadline to be added
     * @param date Due date for the task
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;

        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                    "dd-MM-yyyy HH:mm:ss");
            formattedDate = LocalDateTime.parse(this.date, formatter);
        } catch (DateTimeException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Returns the formatted string representation of the deadline
     * @return Formatted string representation of the deadline
     */
    @Override
    public String store() {
        return String.format("D | %s | %s | %s", this.isDone, this.description,
                formattedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }

    /**
     * Returns the formatted string representation of the deadline
     * @return Formatted string representation of the deadline
     */
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(),
                formattedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }
}
