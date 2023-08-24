public class Deadline extends Task {
    private static final String type = "[D]";
    private String deadline;
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return type + super.toString() + "(by: " + deadline + ")";
    }
}
