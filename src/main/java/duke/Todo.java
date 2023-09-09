package duke;

public class Todo extends Task {
<<<<<<< HEAD
    public Todo (String description) {
        super(description);
    }

=======
    /**
     * Constructs a Todo task.
     *
     * @param description  The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a tring representation of the Todo task.
     *
     * @return  A string representation of the Todo task for the data storage.
     */
>>>>>>> branch-A-JavaDoc
    @Override
    public String toDataString() {
        return "TODO | " + super.toDataString();
    }

<<<<<<< HEAD
=======
    /**
     * Returns a string representation of the Todo task.
     *
     * @return  A string representation of the Todo task for display.
     */
>>>>>>> branch-A-JavaDoc
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
