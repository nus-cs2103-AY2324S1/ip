public class Deadline extends Task {
    private DateTimeHandler by;

    public Deadline(String description, String by) {
        super(description);
        this.by = new DateTimeHandler(by);
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + by.toString();
    }
}
