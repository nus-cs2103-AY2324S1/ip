package duke.task;

/**
 * Todo is an extension of a Task containing only the description of the Todo.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object.
     *
     * @param description A String containing description of the Todo Object.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of the Todo that is writeable to the data file.
     *
     * @return A String object that has a correct format to be written to data file.
     */
    @Override
    public String save() {
        return "T|" + super.save();
    }

    /**
     * Returns a String representation of the Todo Object.
     *
     * @return A String representation of the Todo Object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
