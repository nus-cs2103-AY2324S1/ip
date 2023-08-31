public class Deadline extends Task {

    private String deadlineDetails;

    public Deadline(String task, String deadlineDetails) {
        super(task);
        this.deadlineDetails = deadlineDetails;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDetails + ")";
    }
}
