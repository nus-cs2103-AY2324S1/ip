package duke;
/**
 * To-Do class that has 2 fields, its symbol and name
 */
public class ToDo extends Task {
    private static final String SYMBOL = "[T]";

    /**
     * Constructor for a To-Do task
     * @param name name of the To-Do object
     */
    public ToDo(String name) {
        super(name);
    }
    @Override
    public String toString() {
        return ToDo.SYMBOL + this.getCheckbox() + this.getName();
    }
    @Override
    public String newFormat() {
        return ToDo.SYMBOL + " | " + this.getInt() + " | " + this.getName();
    }
}
