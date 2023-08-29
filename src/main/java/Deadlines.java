public class Deadlines  extends Task{
    protected String by;

    /**
     * A constructor for the public class Deadlines.
     * @param description contains the description of the deadline.
     * @param by contains the time which the deadline must be completed by.
     */
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String saveTaskString() {
        return "D" + super.saveTaskString() + " | " + this.by;
    }
    /**
     * This method converts the value of the deadline into a String type.
     * @return the String representation of the deadline with its type, completion status and completion time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
