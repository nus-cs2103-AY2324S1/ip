package tasks;

import java.util.Objects;

import enums.TaskType;

/**
 * The `TodoTask` class represents a "TODO" task in the Woof application.
 * It is a specific type of task that has no specific due date or event range.
 */
public class TodoTask extends Task {
    /**
     * Constructs a `TodoTask` with the given description.
     *
     * @param description The description of the "TODO" task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Constructs a `TodoTask` with the given description.
     *
     * @param description The description of the "TODO" task.
     * @param isDone      The optional is done to mark a task as per is done.
     */
    public TodoTask(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Retrieves the type of task associated with this object, which is "DEADLINE."
     *
     * @return The task type, which is "TODO" for objects of this class.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }

    /**
     * Retrieves the datetime associated with "TODO", 0 since todo has no datetime associated.
     *
     * @return The start date and time as a long value.
     */
    @Override
    public long getDateTimeLong() {
        return 0;
    }


    /**
     * Generates a string representation of the `TodoTask`.
     *
     * @return A string representation of the "TODO" task, including its status icon and description.
     */
    @Override
    public String toString() {
        return String.format("%s%s", TaskType.TODO.toSymbol(), super.toString());
    }

    /**
     * Checks if this `TodoTask` is equal to another object.
     *
     * @param o The object to compare to.
     * @return `true` if the objects are equal, `false` otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TodoTask)) {
            return false;
        }
        return super.equals(o);
    }

    /**
     * Generates a hash code for this `TodoTask`.
     *
     * @return A hash code for the "TODO" task, including its description and completion status.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}
