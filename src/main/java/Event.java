/**
 * Event encapsulates task that starts at a specific date / time and
 * ends at a specific date/time
 */
public class Event extends Task {
    private String taskName;
    private String startTime;
    private String endTime;

    /**
     * Constructor for creating a task
     *
     * @param taskName name of task.
     */
    public Event(String taskName, String startTime, String endTime) {
        super(taskName);
        this.taskName = taskName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Prints out a message that a Event has been added
     */
    @Override
    public void taskAdded(int noOfTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.toString());
        System.out.println("Now you have " + noOfTask + " tasks in the list.");
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] "
                + this.taskName + " (from: "
                + this.startTime + " to: " + this.endTime + ")";
    }
}
