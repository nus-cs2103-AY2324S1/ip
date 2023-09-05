package tasks;

import enums.TaskType;
import java.util.Objects;

/**
 * The `TodoTask` class represents a "Todo" task in the Duke application.
 * It is a specific type of task that has no specific due date or event range.
 */
public class TodoTask extends Task {
    /**
     * Constructs a `TodoTask` with the given description.
     *
     * @param description The description of the "Todo" task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Generates a string representation of the `TodoTask`.
     *
     * @return A string representation of the "Todo" task,
     * including its status icon and description.
     */
    public String toString() {
        return TaskType.TODO.toSymbol() + super.toString();
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
     * @return A hash code for the "Todo" task, including its description and completion status.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}
