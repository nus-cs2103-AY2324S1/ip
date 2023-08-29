package task;
import exception.InvalidDeadlineException;

/**
 * Deadline class
 */
public class Deadline extends Task {
    // Attribute
    private String deadline;

    // Constructor
    /**
     * The constructor of Deadline class
     * 
     * @param name the name of the deadline
     * @param deadline the deadline of the deadline
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    // Method

    /**
     * Method to return the deadline
     * 
     * @return the deadline
     */
    public String getDeadline() {
        return this.deadline;
    }

    /**
     * Method to get the string representation of deadline
     * 
     * @return the string representation of deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + " )";
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
     * Method to check whether a command is deadline
     * 
     * @param str the command
     * @return whether a command is deadline
     * @throws InvalidDeadlineException
     */
    public static boolean isDeadline(String str) throws InvalidDeadlineException {
        if(str.split(" ")[0].equals("deadline")) {
            if(str.indexOf("/by ") == -1) {
                throw new InvalidDeadlineException();
            } else {
                return true;
            }
        }
        return false;
    }
}
