package tasks;

public class Deadline extends Task {
    String dueDate;

    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    public Deadline(String taskName, boolean done, String dueDate) {
        super(taskName, done);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }

}
