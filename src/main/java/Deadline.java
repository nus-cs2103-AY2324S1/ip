public class Deadline extends Tasks {
    private String deadline;

    public Deadline(String name, boolean isMarked, String deadline) {
        super(name, isMarked);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String status = isMarked ? "[X]" : "[ ]";
        return "[D]" + status + " " + name + " (by: " + deadline + ")";
    }
}
