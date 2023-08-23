public class Todos extends ListItem {

    /**
     * Constructor for Todos.
     */
    public Todos(String text) {
        super(text);
    }

    /**
     * Returns string representation of Todos.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
