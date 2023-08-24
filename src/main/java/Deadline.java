public class Deadline extends Task {
    private String deadlineDateString;
    public Deadline(String name, boolean isDone, String deadlineDateString) {
        super(name, isDone);
        this.deadlineDateString = deadlineDateString;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.deadlineDateString);
    }
}
