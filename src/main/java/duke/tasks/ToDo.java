package duke.tasks;

import duke.exceptions.DukeException;

public class ToDo extends Task{
    private final String descr;
    public ToDo(String descr) {
        super(descr);
        this.descr = descr;
    }

    public void checkValidity() throws DukeException {
        String[] descrArr = descr.split(" "); //you get 0: taskName, 1: start, 2: end
        if (descrArr.length < 2) {
            throw new DukeException("You are missing todo details");
        }
    }
    public String writtenFormat() {
        return "T | " + super.status() + "| " + this.descr;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
