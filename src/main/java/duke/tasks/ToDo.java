package duke.tasks;

import duke.tasks.Task;

public class ToDo extends Task {

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
