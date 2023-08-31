package Utils;

public class Deadline extends Task {

    private String start;

    protected Deadline(String title, String start) {
        super(title);
        this.start = start;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + start + ")";
    }
}
