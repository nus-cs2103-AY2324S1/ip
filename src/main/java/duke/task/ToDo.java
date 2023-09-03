package duke.task;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Classic to-do task that only contains the task string */
public class ToDo extends Task {

    /**
     * Initialize To-Do class.
     * @param task Task.
     */
    public ToDo(@JsonProperty("task") String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
