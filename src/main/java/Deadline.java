public class Deadline extends Task {
    private String due;
    public Deadline(String desc, String due) {
        super(desc);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.due + ")";
    }
    // D | 0 | return book | June 6th
    @Override
    public String toMemory() { return "D " + super.getStatus() + super.getDescription() + " | " + due; }

}
