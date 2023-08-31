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
    private boolean completed;
    /**
     * Creates a task with the given name
     * @param taskName Name of task
     */
    public Task(String taskName) {
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
     *     of task to.
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
<<<<<<< HEAD
    /**
     * Used to get the date of the task object.
     * @return Date of task
     */
=======
    public String getName() {
        return this.taskName;
    }
>>>>>>> branch-Level-9
    public abstract LocalDate getDate();
    /**
     * String representation of Task
     * @return String representation of task
     */
    @Override
    public String toString() {
        return this.isCompleted()
            ? "[" + this.oneLetterAbbrev + "][X] " + this.taskName
            : "[" + this.oneLetterAbbrev + "][ ] " + this.taskName;
    }
}
