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
    public String toString(boolean isWritten) {
        String completionStr = super.isDone() ? "1" : "0";
        return "D" + " | " + completionStr + " | " + super.getName() + " | " + this.deadlineDateString;
    }
}
