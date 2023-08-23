public class Deadline extends Task {
    String endTime;
    public Deadline(String endTime, String description) {
        super(description);
        this.endTime = endTime;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.endTime + ")";
    }

}
