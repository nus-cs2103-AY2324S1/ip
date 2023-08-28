public class Deadline extends Task {
    private String deadline;
    private String symbol = "[D]";
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }
    @Override
    public String newFormat() {
        return this.symbol + " | " + this.getInt() + " | " + this.getName() + " | " + this.deadline;
    }
    @Override
    public String toString() {
        return this.symbol + this.getCheckbox() + this.getName() + " (by: " + deadline + ")";
    }
}