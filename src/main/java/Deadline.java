public class Deadline extends Task {
    private String due;

    public Deadline(String description) {
        super(description.split(" /by ")[0]);
        this.due = description.split(" /by ")[1];
    }

    @Override
    public String toString() {
        String done = this.done ? "[X]" : "[ ]";
        return "[D]" + done + " " + this.name
                + " (by: " + this.due + ")";
    }
}
