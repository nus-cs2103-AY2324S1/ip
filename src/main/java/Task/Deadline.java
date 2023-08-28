package Task;

import DukeException.InvalidTaskFormatException;

public class Deadline extends Task{
    final private String deadlineTime;

    public Deadline(String task) throws DukeException.EmptyTaskDescException, InvalidTaskFormatException {
        super(task.split("/")[0]);
        String[] taskComponents = task.split("/");
        if(taskComponents.length != 2) {
            throw new InvalidTaskFormatException("This isn't the format for a deadline!");
        }
        this.deadlineTime = super.insertColonInTime(taskComponents[1]);
    }

    public String toString() {
        return "[D]" + super.toString() + "(" + deadlineTime + ")";
    }
}
