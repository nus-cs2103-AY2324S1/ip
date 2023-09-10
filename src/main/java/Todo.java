public class Todo extends Task {

    /**
     * Constructs a Todo with the specified name.
     *
     * @param name The name of the todo task to be done.
     */
    public Todo(String name) {
        super(name);
    }

    public static Todo fromFileFormat(String[] parts) {
        boolean isDone = "1".equals(parts[1].trim());
        String name = parts[2].trim();
        Todo todo = new Todo(name);
        if (isDone) {
            todo.markAsDone();
        }
        return todo;
    }

    @Override
    public String toFileFormat() {
        return "T|" + (isDone ? "1" : "0") + "|" + this.name + "||";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}