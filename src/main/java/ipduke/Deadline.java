package ipduke;

public class Deadline extends Task {
    private String duedate;
    Deadline(String task, String duedate, boolean done) {
        super(task, done);
        this.duedate = duedate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.duedate);
    }

    @Override
    public String getTaskFileString() {
        return "D" + " , " + super.getTaskFileString() + " , " + this.duedate;
    }
}
