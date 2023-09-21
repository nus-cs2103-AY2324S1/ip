package rua.task;

import java.util.ArrayList;

/**
 * Represents an Event Task.
 */
public class Todo extends Task {
    private static final String TYPE = "T";

    /**
     * Constructs a Todo object (assuming unmarked and no tag).
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
     * Constructs a Todo task object.
     *
     * @param description A String to describe the task.
     * @param isMarked A boolean to indicate whether it is marked.
     * @param tags An arraylist of tags.
     */
    public Todo(String description, Boolean isMarked, ArrayList<String> tags) {
        super(description, isMarked, tags);
    }

    /**
     * {@inheritDoc}
     * It returns "T" for Todo type.
     *
     * @return The task type ("T" for Todo type).
     */
    @Override
    public String getType() {
        return TYPE;
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
     * @return A string representing this Todo task in the format: [T][ marked indicator ] description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
