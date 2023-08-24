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
     * Method to get the string representation of deadline
     * 
     * @return the string representation of deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + " )";
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
