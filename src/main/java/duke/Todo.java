package duke;

import java.util.Objects;

/**
 * Represents a Todo task, which is a type of task with no specific deadline.
 * It inherits from the Task class.
 */
public class Todo extends Task{

    /**
     * Constructs a Todo object with the provided task description.
     *
     * @param desc The description of the Todo task.
     */
    public Todo(String desc) {
        super(desc);
    }

    /**
     * Returns a string representation of the Todo task in a format suitable for saving to a file.
     *
     * @return A formatted string representing the Todo task's details for saving to file.
     */
    @Override
    public String saveString() {
        String completedString = isCompleted ? "X|" : " |";
        return "T|" + completedString + description;
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A formatted string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Indicates whether some other object is "equal to" this Todo instance.
     *
     * This method overrides the default equals implementation from the Object class.
     * Two Todo instances are considered equal if they meet the following criteria:
     * 1. They reference the same object (i.e., identical references).
     * 2. The other object is an instance of the Todo class.
     * 3. The 'isCompleted' status and 'description' fields of both Todo instances are equal.
     *
     * @param o The object to compare for equality with this Todo instance.
     * @return true if the two Todo instances are equal based on the specified criteria,
     *         false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Todo)) {
            return false;
        }

        Todo otherTodo = (Todo) o;

        return this.isCompleted == otherTodo.isCompleted && this.description.equals(otherTodo.description);
    }

    /**
     * Returns a hash code value for the Todo object.
     *
     * This method generates a hash code based on the 'isCompleted' status and 'description'
     * of the Todo object. It combines these two properties to create a unique hash code.
     *
     * @return The hash code value for the Todo object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(isCompleted, description);
    }
}
