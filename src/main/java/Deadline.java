public class Deadline extends Task {
    String due;

    public Deadline(String d, String due) {
        super(d);
        this.due = due;
    }

    @Override
    public String toString() {
        String marker = "[ ]";
        if (this.completed) marker = "[X]";
        return  "[D]" + marker + " " +
                this.description +
                "(by:" + this.due + ")\n";
    }
}
