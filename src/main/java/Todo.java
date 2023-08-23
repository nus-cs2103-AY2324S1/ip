public class Todo extends Task {
    /**
     * Constructor for the Todo class.
     *
     * @param name The name of the todo task.
     */
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[T][X] " + this.name;
        } else {
            return "[T][ ] " + this.name;
        }
    }
}
