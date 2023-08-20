public class Deadline extends Task{
    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public String getType() {
        return "D";
    }

    public String getDeadline() {
        return deadline;
    }

    public String toString() {
        return "[" + this.getType() + "]" + "[" + this.getStatus() + "]" + this.getName() + " (by: " + this.getDeadline() + ")";
    }
}
