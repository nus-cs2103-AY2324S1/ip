public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline() {
        super("");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String toFileString() {
        return "D | " + super.getStatusIcon() + " | " + description + " | " + by;
    }

    public void fromFileString(String fileString) {
        String[] fileStringArray = fileString.split(" \\| ");
        this.setStatusIcon(fileStringArray[1]);
        this.description = fileStringArray[2];
        this.by = fileStringArray[3];
    }

}
