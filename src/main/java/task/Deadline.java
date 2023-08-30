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

    /**
     * Checks whether a command is deadline
     * 
     * @param str the command
     * @return whether a command is deadline
     * @throws InvalidDeadlineException
     */
    public static boolean isDeadline(String str) throws InvalidDeadlineException {
        try{
            if(str.split(" ")[0].equals("deadline")) {
                if(str.indexOf("/by ") != -1) {
                    LocalDate date = LocalDate.parse(str.split("/by ")[1]);
                    return true;
                } else {
                    throw new InvalidDeadlineException();
                }
            }
            return false;
        } catch(DateTimeParseException e) {
            throw new InvalidDeadlineException();
        }
    }
}
