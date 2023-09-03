package duke;

import duke.DukeException;
import duke.Task;
import duke.TaskType;

public class ToDo extends Task {

    public ToDo(String description) throws DukeException {
        super(description, TaskType.TODO);

        if (description.trim().isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}