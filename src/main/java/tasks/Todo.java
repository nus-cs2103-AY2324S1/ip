package tasks;

/**
 * A child class to Task, this in particular is for todo tasks.
 */
public class Todo extends Task {
    /**
     * A constructor method for Todo.
     * @param name the name of the task.
     */
    public Todo(String name) {
        super(name);
        super.setType("T");
    }
}
