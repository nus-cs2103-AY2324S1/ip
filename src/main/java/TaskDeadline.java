/**
 * Representation of a deadline task
 * recorded by the chatbot.
 * 
 * @author Alvis Ng (supermii2)
 */
public class TaskDeadline extends Task {
    /** Deadline time of the task */
    private String deadlineTime;
    /**
     * Creates a deadline task.
     * @param taskName Name of task
     * @param deadlineTime Deadline
     */
    TaskDeadline(String taskName, String deadlineTime) {
        super(taskName);
        super.oneLetterAbbrev = "D";
        this.deadlineTime = deadlineTime;
    }
    @Override
    /**
     * String representation of Deadline
     * @return String representation of deadline
     */
    public String toString() {
        return super.toString() + 
        " (by: " + this.deadlineTime + ")";
    }
}
