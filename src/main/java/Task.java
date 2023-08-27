/**
 * Representation of a task
 * recorded by the chatbot.
 * 
 * @author Alvis Ng (supermii2)
 */
public abstract class Task {
    /** Name of task */
    protected String taskName;
    /** Whether the task has been completed */
    private boolean completed;
    /** One letter abbreviation for the task. */
    protected String oneLetterAbbrev;
    /**
     * Creates a task with the given name
     * @param taskName Name of task
     */
    Task(String taskName) {
        this.taskName = taskName;
        completed = false;
    }
    /**
     * Checks if a task is completed
     * @return State of completeness of task
     */
    public boolean isCompleted() {
        return this.completed;
    }
    /**
     * Sets the completion state of task
     * to the given boolean
     * @param completed State to change completion state
     * of task to.
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    @Override
    /**
     * String representation of Task
     * @return String representation of task
     */
    public String toString() {
        return this.isCompleted() 
        ? "[" + this.oneLetterAbbrev + "][X] " + this.taskName 
        : "[" + this.oneLetterAbbrev + "][ ] " + this.taskName;
    }
}
