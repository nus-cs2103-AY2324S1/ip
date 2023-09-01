package ax.task;

public class Todos extends ListItem {

    /**
     * Constructor for ax.task.Todos.
     */
    public Todos(String text) {
        super(text);
    }

    /**
     * Returns string representation of ax.task.Todos.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
