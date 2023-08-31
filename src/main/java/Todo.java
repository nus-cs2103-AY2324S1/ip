public class Todo extends Task {
    /**
     * Constructor for the Todo class.
     *
     * @param name The name of the todo task.
     * @param done Whether the task is marked done or not.
     */
    public Todo(String name, boolean done) {
        super(name, done);
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[T][X] " + this.name;
        } else {
            return "[T][ ] " + this.name;
        }
    }

    public String displayableForm() {
        return this.toString();
    }
}
