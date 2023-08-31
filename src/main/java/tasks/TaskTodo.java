package tasks;
import java.time.LocalDate;

/**
 * Representation of a ToDo task
 * recorded by the chatbot.
 * 
 * @author Alvis Ng (supermii2)
 */
public class TaskTodo extends Task {
    @Override
    /**
     * Used to get the date of the task object.
     * @return Null, as todo has no date
     */
    public LocalDate getDate() {
        return null;
    }
    /**
     * Creates a todo task.
     * @param taskName Name of task
     */
    public TaskTodo(String taskName) {
        super(taskName);
        super.oneLetterAbbrev = "T";
    }
}
