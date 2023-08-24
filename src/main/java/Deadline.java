public class Deadline extends Task {
    private String due;
    public Deadline(String desc, String due) {
        super(desc);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.due + ")";
    }

}
