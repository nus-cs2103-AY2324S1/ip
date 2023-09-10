package koko;

/**
 * Represents a Todo task, which is a subclass of Task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo with the specified name.
     *
     * @param name The name of the todo task to be done.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Creates a Todo task object from its file representation.
     * @param parts An array of String parts from the file line.
     * @return A new Todo object.
     */
    public static Todo fromFileFormat(String[] parts) {
        boolean isDone = "1".equals(parts[1].trim());
        String name = parts[2].trim();
        Todo todo = new Todo(name);
        if (isDone) {
            todo.markAsDone();
        }
        return todo;
    }

    /**
     * Returns the file representation of the Todo task.
     * @return A string representing the task in the file format.
     */
    @Override
    public String toFileFormat() {
        return "T|" + (isDone ? "1" : "0") + "|" + this.name + "||";
    }

    /**
     * Returns the string representation of the Todo task.
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}