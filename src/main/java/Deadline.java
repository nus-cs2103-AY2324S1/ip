public class Deadline extends Task{
    /**
     * the type Icon
     */
    private String type = "D";
    /**
     * the time the deadline must be finished
     */
    private String by = "";

    /**
     * constructor for Event task
     * @param description the text stored
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * override the toString method
     * @return a string
     */
    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String toDataString() {
        return this.type + " / " + super.toDataString() + " / " + this.by;
    }
}
