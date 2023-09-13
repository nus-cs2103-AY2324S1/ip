package duke.task;

import duke.DukeException;


/**
 * The Todo class represents a todo task.
 * It is a subclass of the Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public void updateDetails(String field, String details) throws DukeException {
        if (field.equals("desc")) {
            this.description = details;
        } else {
            throw new DukeException("OOPS!!! Only /desc flag allowed.");
        }
    }

    @Override
    public String toData() {
        String done = String.valueOf(this.isDone ? 1 : 0);
        return "T | " + done + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
