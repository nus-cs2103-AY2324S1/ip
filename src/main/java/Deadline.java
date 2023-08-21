/**
 * Deadline encapsulates task that need to be done before a specific date or time
 */
public class Deadline extends Task {
    private String taskName;
    private String deadline;

    /**
     * Constructor for creating a task
     *
     * @param taskName name of task.
     */
    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.taskName = taskName;
        this.deadline = deadline;
    }

    /**
     * Prints out a message that a deadline task has been added
     */
    @Override
    public void taskAdded(int noOfTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.toString());
        System.out.println("Now you have " + noOfTask + " tasks in the list.");
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] "
                + this.taskName + " (by: " + this.deadline + ")";
    }
}
