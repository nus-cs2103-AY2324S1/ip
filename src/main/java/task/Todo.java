package task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a to-do task.
 */
public class Todo extends Task {
    /**
     * Constructs a to-do Task with taskName.
     *
     * @param taskName the task name of the to-do task
     */
    @JsonCreator
    public Todo(@JsonProperty("taskName") String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
