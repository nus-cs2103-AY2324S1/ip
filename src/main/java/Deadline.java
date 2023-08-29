public class Deadline extends Task {
    private String date;
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.date);
    }

    @Override
    public String formatData() {
        return String.format("D | %d | %s | %s\n", this.isDone ? 1 : 0, this.description, this.date);
    }
}