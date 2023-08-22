/**
 * Representation of a deadline task
 * recorded by the chatbot.
 * 
 * @author Alvis Ng (supermii2)
 */
public class TaskEvent extends Task{
    /**Start and end times of events */
    String startTime, endTime;
    /**
     * Creates a deadline task.
     * @param taskName Name of task
     * @param startTime Start time of task
     * @param endTime End time of task
     */
    TaskEvent(String taskName, String startTime, String endTime) {
        super(taskName);
        super.oneLetterAbbrev = "E";
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    /**
     * String representation of Event
     * @return String representation of Event
     */
    public String toString() {
        return super.toString() + 
        " (from: " + this.startTime + " to: " + this.endTime + ")";
    }    
}
