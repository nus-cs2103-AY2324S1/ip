package duke.tasks;

import duke.tasks.Task;
import duke.exceptions.DukeException;

/**
 * The ToDo class, which is a task to be done.
 */
public class ToDo extends Task {

    /**
     * Instantiates a new ToDo.
     *
     * @param name the description of the ToDo
     * @throws DukeException a duke exception for when the description is empty
     */
    public ToDo(String name) throws DukeException {
        super(name);
        if (name.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public String dataString() {
        if (this.isdone()) {
            return "T : 1 : " + this.getname();
        } else {
            return "T : 0 : " + this.getname();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
