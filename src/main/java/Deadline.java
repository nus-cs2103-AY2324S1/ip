public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline initializeFromInput(String input) {
        String[] processed = input.split("deadline")[1].split("/by");
        String taskName = processed[0];
        String doBy = processed[1].strip();
        return new Deadline(taskName, doBy);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
