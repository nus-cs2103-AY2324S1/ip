public class Deadline extends Task {

    protected String by;


    public Deadline(String description, String by) {
        super(description, Type.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {

        return "[D]" + "[" + getStatusIcon() + "]" + description + "(" + by + ")";
    }
}
