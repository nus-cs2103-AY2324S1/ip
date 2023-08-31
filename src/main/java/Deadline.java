public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toTxtString() {
//        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + by;
        return "[D] | [" + (this.isDone ? "X": " ") + "] | " + this.description + " | " + by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
