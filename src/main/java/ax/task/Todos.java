package ax.task;

/**
 * Todos Class contains Todos information
 */
public class Todos extends ListItem implements Reminders {

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

    /**
     * todos only return false, as there is no due date associated with it
     * @return false
     */
    public boolean isDue() {
        return false;
    }
}
