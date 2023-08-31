package task;

public class Deadline extends Task {

    private final String dateOfDeadline;

    public Deadline(boolean completed, String nameOfTask, String dateOfDeadline) {
        super(completed, nameOfTask);
        this.dateOfDeadline = dateOfDeadline;
    }

    @Override
    public String toString() {
        if (completed) {
            return "[D][X] " + nameOfTask + " (by: " + dateOfDeadline + ")";
        } else {
            return "[D][ ] " + nameOfTask + " (by: " + dateOfDeadline + ")";
        }
    }

    @Override
    public String typeOfString() {
        return "D ";
    }

    @Override
    public String taskDetailsString() {
        return super.nameOfTask + " |" + " " + dateOfDeadline;
    }
}
