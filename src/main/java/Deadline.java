public class Deadline extends Task{
    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    public String convertTaskToString() {
        return "D | " + (super.isDone() ? "1" : "0") + " | " + super.getName() + " | " + this.by;
    }
}
