package chatbot.task;

/**
 * class Todo extends class Task which consist of variable for todo.
 */
public class Todos extends Task {

    /**
     * constructor for class Todos.
     *
     * @param description string for deadline's description
     */
    public Todos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    @Override
    public String toFileFormat() {
        String done = isDone ? "1" : "0";
        return String.format("T | %s | %s", done, description);
    }
}
