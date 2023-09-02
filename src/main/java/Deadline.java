public class Deadline extends Task {
    private DateTimeHandler by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = new DateTimeHandler(by);
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + by.toString();
    }
}
