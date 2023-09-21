package duke;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task of type Todo.
 */
public class Todo extends TaskType {

    /**
     * Constructor for the Todo class.
     *
     * @param task Description of task
     * @param isCompleted Whether of not the task has been completed
     */

    public Todo(String task, boolean isCompleted) {
        super(task, isCompleted, "");
    }
    public String toShortString() {
        return "T";
    }
    public String getFormattedDatetime(DateTimeFormatter formatter) {
        return "";
    }
}