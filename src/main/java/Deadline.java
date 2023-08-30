public class Deadline extends Task {
    private String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    @Override
    public  String saveString() {
        return "D" + super.saveString() + " | " + this.deadline;
    }
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline);
    }
}
