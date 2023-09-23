package duke.task;

import duke.exception.DukeException;

/**
 * A simple task only holding the description.
 */
public class Todo extends Task {

    /**
     * Constructs a duke.task.Todo task
     *
     * @param description The description of the task.
     */
    public Todo(String description) throws DukeException {
        super(description);
        this.taskType = taskType.TODO;
    }

    /**
     * Returns a string representation of task
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return this.isDone ? "[T][X] " + this.description : "[T][ ] " + this.description;
    }

    /**
     * Used for easier readability during
     * storing into the file.
     *
     * @return the raw version of the task for storing.
     */
    @Override
    public String getRaw() {
        return "T" + "|" + isDone + "|" + this.description;
    }
}
