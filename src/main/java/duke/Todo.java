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
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link HashMap}.
     * <p>
     * The general contract of {@code hashCode} is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during
     *     an execution of a Java application, the {@code hashCode} method
     *     must consistently return the same integer, provided no information
     *     used in {@code equals} comparisons on the object is modified.
     *     This integer need not remain consistent from one execution of an
     *     application to another execution of the same application.
     * <li>If two objects are equal according to the {@code equals(Object)}
     *     method, then calling the {@code hashCode} method on each of
     *     the two objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal
     *     according to the {@link Object#equals(Object)}
     *     method, then calling the {@code hashCode} method on each of the
     *     two objects must produce distinct integer results.  However, the
     *     programmer should be aware that producing distinct integer results
     *     for unequal objects may improve the performance of hash tables.
     * </ul>
     * <p>
     * As much as is reasonably practical, the hashCode method defined
     * by class {@code Object} does return distinct integers for
     * distinct objects. (The hashCode may or may not be implemented
     * as some function of an object's memory address at some point
     * in time.)
     *
     * @return a hash code value for this object.
     * @see Object#equals(Object)
     * @see System#identityHashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(isCompleted, description);
    }
}
