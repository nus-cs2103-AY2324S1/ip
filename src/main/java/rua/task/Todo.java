package rua.task;

public class Todo extends Task {
    /**
     * Constructs a Todo object (assuming unmarked).
     *
     * @param description A String to describe the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo task object.
     *
     * @param description A String to describe the task.
     * @param isMarked A boolean to indicate whether it is marked.
     */
    public Todo(String description, Boolean isMarked) {
        super(description, isMarked);
    }

    /**
     * {@inheritDoc}
     * It returns "T" for Todo type.
     *
     * @return The task type ("T" for Todo type).
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * {@inheritDoc}
     *
     * @return A new Todo task object with the same description but it is marked.
     */
    @Override
    public Todo setMarked() {
        return new Todo(this.description, true);
    }

    /**
     * {@inheritDoc}
     *
     * @return A new Todo task object with the same description but it is unmarked.
     */
    @Override
    public Todo setUnmarked() {
        return new Todo(this.description, false);
    }

    /**
     * Compares the task with other objects and return true if they are the same Todo task.
     *
     * @param o Another object to be compared with.
     * @return A boolean indicating whether they are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Todo)) {
            return false;
        }
        Todo c = (Todo) o;

        // Compare the data members and return accordingly
        return c.description.equals(this.description)
                && c.isMarked.equals(this.isMarked);
    }

    /**
     * Returns a string to represent this Todo task.
     *
     * @return A string representing this Todo task in the format:
     * [T][ marked indicator ] description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
