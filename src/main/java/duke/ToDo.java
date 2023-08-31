package duke;


/**
 * /nTodo class that inherits from parent class Task. Stores information about the task.
 */
public class ToDo extends Task {
    private String identifier;

    public ToDo(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
        this.identifier = "[T]";
    }

    public String toString() {
        return this.identifier + super.toString();
    }
}
