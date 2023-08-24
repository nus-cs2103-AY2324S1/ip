public class Deadline extends Task {
    String dateTime;
    public Deadline(String task, String dateTime) {
        super(task);
        this.dateTime=dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.dateTime + ")";
    }
}
