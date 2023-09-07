package tasks;
import java.io.Serializable;
import java.time.LocalDate;
/**
 * Representation of a task
 * recorded by the chatbot.
 * @author Alvis Ng (supermii2)
 */
public abstract class Task implements Serializable {
    /** Name of task */
    protected String taskName;
    /** One letter abbreviation for the task. */
    protected String oneLetterAbbrev;
    /** Whether the task has been completed */
    private boolean isCompleted;
    /**
     * Creates a task with the given name
     * @param taskName Name of task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        isCompleted = false;
    }
    /**
     * Checks if a task is completed
     * @return State of completeness of task
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }
    /**
     * Sets the completion state of task
     * to the given boolean
     * @param completed State to change completion state
     *     of task to.
     */
    public void setCompleted(boolean completed) {
        if (this.isCompleted() == completed) {
            if (completed) {
                throw new IllegalArgumentException("Task is already marked!");
            } else {
                throw new IllegalArgumentException("Task is already unmarked");
            }
        }
        this.isCompleted = completed;
    }
    public String getName() {
        return this.taskName;
    }
    public abstract LocalDate getDate();
    /**
     * String representation of Task
     * @return String representation of task
     */
    @Override
    public String toString() {
        String marked;
        if (this.isCompleted()) {
            marked = "X";
        } else {
            marked = " ";
        }
        return String.format("[%s][%s] %s",this.oneLetterAbbrev, marked, this.taskName);
    }
}
