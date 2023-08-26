package data;

public class Deadline extends Task {
    private By by;

    public void setBy(String by) {
        this.by = new By(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}