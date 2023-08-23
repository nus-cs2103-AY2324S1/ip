package ipduke;

public class Deadline extends Task {
    private String datetime;
    Deadline(String taskDetails) {
        super(taskDetails.split(" /by ")[0]);
        this.datetime = taskDetails.split(" /by ")[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.datetime);
    }
}
