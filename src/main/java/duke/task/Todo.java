package duke.task;

/**
 * The Todo class represents a to-do task in the Duke application.
 * It is a subclass of the Task class and inherits its properties and methods.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo object with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Reads a todo task from a file and returns a corresponding Todo object.
     *
     * @param components An array of components parsed from a data file line.
     * @return A Todo object representing the to-do task read from the file.
     */
    public static Todo readFromFile(String[] components) {
        boolean isDone = components[1].equals("1");
        Todo todo = new Todo(components[2]);
        if(isDone) {
            todo.markDone();
        }
        return todo;
    }

    /**
     * Returns the task in the format suitable for writing to a data file.
     *
     * @return A string in the file format representing the todo task.
     */
    @Override
    public String writeFileFormat() {
        return "T|" + super.writeFileFormat();
    }

    /**
     * Returns a string representation of the todo task, including its status icon and description.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
