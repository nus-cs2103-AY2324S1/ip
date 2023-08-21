/**
 * This is the Deadline class, a child class of Task class
 * @author Selwyn
 */
public class Deadline extends Task{
    /**
     * Field representing the date and time of the deadline
     */
    protected String endDateTime;

    /**
     * Constructor for a Deadline task
     *
     * @param detail
     */
    public Deadline(String detail, String endDateTime) {
        super(detail);
        this.endDateTime = endDateTime;
    }

    /**
     * This method displays and prints the completion status, details and the date & time of the deadline
     */
    @Override
    public void displayTask() {
        System.out.print("[D] ");
        System.out.print(this.isDone ? "[X] " : "[ ] ");
        System.out.print(this.detail + " ");
        System.out.println("(by: " + this.endDateTime + ")");
    }
}
