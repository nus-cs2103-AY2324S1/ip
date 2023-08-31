package task;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exception.InvalidDeadlineException;

/**
 * Deadline class
 */
public class Deadline extends Task {
    // Attribute
    private LocalDate deadline;

    // Constructor
    /**
     * The constructor of Deadline class
     * 
     * @param name the name of the deadline
     * @param deadline the deadline of the deadline
     */
    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    // Method

    /**
     * Creates a new deadline based on the message
     * 
     * @param message the message to create the new deadline
     * @return the new deadline
     * @throws InvalidDeadlineException
     */
    public static Deadline create(String message) throws InvalidDeadlineException {
        try {
            String name = message.substring(9, message.indexOf("/by "));
            String deadlineString = message.substring(message.indexOf("/by ") + 4);
            LocalDate deadlineDate = LocalDate.parse(deadlineString);
            return new Deadline(name, deadlineDate);
        } catch(IndexOutOfBoundsException e) {
            throw new InvalidDeadlineException();
        } catch(DateTimeParseException e) {
            throw new InvalidDeadlineException();
        }
    }

    /**
     * Method to get the string representation of deadline
     * 
     * @return the string representation of deadline
     */
    @Override
    public String toString() {
        String deadlineMonth = deadline.getMonth().toString().substring(0, 3);
        String deadlineDay = deadline.toString().split("-")[2];
        String deadlineYear = deadline.toString().split("-")[0];
        return "[D]" + super.toString() + " (by: " + deadlineMonth + " " 
            + deadlineDay + " " + deadlineYear + " )";
    }

    /**
     * Method to return the string format of the deadline in the storage
     * 
     * @return the string format of the deadline in the storage
     */
    @Override
    public String storeInString() {
        return "D | " + (this.getMark() ? "1 | " : "0 | ") + this.getName() + " | " + this.deadline;
    }
}
