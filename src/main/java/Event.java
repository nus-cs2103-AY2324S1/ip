/**
 * This is the Event class, a child class of Task class
 * @author Selwyn
 */
public class Event extends Task{
    /**
     * Field representing the start date & time of the event
     */
    protected String startDateTime;

    /**
     * Field representing the end date & time of the event
     */
    protected String endDateTime;

    /**
     * Constructor for an Event task
     *
     * @param detail
     */
    public Event(String detail, String startDateTime, String endDateTime) {
        super(detail);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * This method displays and prints the completion status, details, start and end dates & times of the event
     */
    @Override
    public void displayTask() {
        System.out.print("[E] ");
        System.out.print(this.isDone ? "[X] " : "[ ] ");
        System.out.print(this.detail + " ");
        System.out.println("(from: " + this.startDateTime + " to: " + this.endDateTime + ")");
    }
}
