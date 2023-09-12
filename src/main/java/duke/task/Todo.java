package duke.task;

import java.util.Objects;

/**
 * Represents a Todo Task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getSaveString() {
        return String.format("%d todo %s", getIsDone() ? 1 : 0, getDescription().trim());
    }

    /**
     * Check if given object is equal to this object.
     * They are equal if,
     * <ul>
     *     <li>They are the same object</li>
     *     <li>They have the same description</li>
     * </ul>
     *
     * @param obj the object to be compared.
     * @return true if equal, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Todo) {
            Todo d = (Todo) obj;
            return Objects.equals(this.getDescription(), d.getDescription());
        } else {
            return false;
        }
    }
}
