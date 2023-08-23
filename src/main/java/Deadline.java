public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this);
        System.out.println("Now you have " + super.size + " tasks in the list.");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}