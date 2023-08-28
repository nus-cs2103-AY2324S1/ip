package task;

import task.Task;

public class Deadline extends Task {

    private String dateOfDeadline;

    public Deadline(String nameOfTask, String dateOfDeadline) {
        super(nameOfTask);
        this.dateOfDeadline = dateOfDeadline;
    }

    @Override
    public String toString() {
        if (completed) {
            return "[D][X] " + nameOfTask + "(by:" + dateOfDeadline + ")";
        } else {
            return "[D][ ] " + nameOfTask + "(by:" + dateOfDeadline + ")";
        }
    }
}
