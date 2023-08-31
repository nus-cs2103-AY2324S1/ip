package duke;

public class Todo extends Task {
    /**
     * Constructor for Todo.
     *
     * @param name Description of the Todo.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructor for Todo if a done status is supplied.
     *
     * @param name Description of the Todo.
     * @param isDone Boolean representing whether the Todo is done.
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Gets String representation of the Todo.
     *
     * @return String representation of the Todo.
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Gets String representation of the Todo in the storage.
     *
     * @return String representation of the Todo in the storage.
     */
    public String toStringStorage() {
        String nameField = this.getName();
        String isDoneField = this.isDone() ? "1" : "0";
        return "T|" + isDoneField + "|" + nameField;
    }
}
