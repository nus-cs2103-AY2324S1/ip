public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String first = "[D]" + "[" + this.getStatusIcon() + "] " + this.description + " ";
        String second = "(by: " + this.by + ")";
        return first + second;
    }

    @Override
    public String stringInFile() {
        int status = super.isDone ? 1 : 0;
        return "D | " + status + " | " + this.description + "| " + this.by;
    }
}
