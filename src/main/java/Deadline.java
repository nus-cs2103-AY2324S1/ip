public class Deadline extends Task {
    String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, String date, Boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }

}
