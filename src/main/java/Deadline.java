public class Deadline extends Task {
    private String due;

    public Deadline(String task, String due) {
        super(task);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + due + ")";
    }

}
