package duke.tasks;

import duke.exceptions.DukeException;

public class ToDo extends Task{
    private final String descr;
    public ToDo(String descr) {
        super(descr);
        this.descr = descr;
    }

    /**
     * Checks that the task is a valid ToDo with a task description.
     *
     * @throws DukeException if input is invalid.
     */
    public void checkValidity() throws DukeException {
        String[] descrArr = descr.split(" ");
        assert descrArr.length > 2 : "Missing todo details";
        if (descrArr.length < 2) {
            throw new DukeException("You are missing todo details");
        }
    }

    /**
     * Reformats todos to be ready to be written into tasks.txt.
     *
     * @return the reformatted todo.
     */
    public String writtenFormat() {
        String[] descrArr = descr.split("todo ");
        return "T | " + super.status() + " | " + descrArr[1];
    }

    /**
     * Converts task to string.
     *
     * @return formatted String version of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
