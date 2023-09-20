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

    /**
     * Checks if this Todo is equal to another object.
     *
     * @param obj The other object being compared to.
     * @return true if the other object is a Todo with the same string representation, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Todo)) {
            return false;
        }

        Todo otherTodo = (Todo) obj;

        return this.toString().equals(otherTodo.toString());
    }
}
