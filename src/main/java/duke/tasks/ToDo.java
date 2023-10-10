package duke.tasks;

import duke.exceptions.DukeException;

import java.time.LocalDate;

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

    /**
     * Returns the String description of a task suitable for file storage.
     *
     * @return the task String
     */
    public String dataString() {
        if (this.isdone()) {
            return "T : 1 : " + this.getname();
        } else {
            return "T : 0 : " + this.getname();
        }
    }

    /**
     * Returns false since Todos are not time sensitive.
     *
     * @param date the date
     * @return the false since date does not match
     */
    @Override
    public boolean checkDate(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
