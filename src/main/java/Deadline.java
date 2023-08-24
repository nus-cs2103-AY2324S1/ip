public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * This method overrides the toString method from the Object class.
     * It returns a string representation of this object.
     *
     * @return A string representation of this object.
     */

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
