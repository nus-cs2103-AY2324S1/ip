package tasks;
import java.time.LocalDate;

/**
 * Representation of a ToDo task
 * recorded by the chatbot.
 * @author Alvis Ng (supermii2)
 */
public class TaskTodo extends Task {
    /**
     * Creates a todo task.
     * @param taskName Name of task
     */
    public TaskTodo(String taskName) {
        super(taskName);
        super.oneLetterAbbrev = "T";
    }
    @Override
    public LocalDate getDate() {
        return null;
    }
}
