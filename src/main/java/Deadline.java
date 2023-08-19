public class Deadline extends Task{
    private String due;

    public Deadline(String content, String due) {
        super(content);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + due + ")";
    }
}
