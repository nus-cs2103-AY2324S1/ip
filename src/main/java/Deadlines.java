public class Deadlines extends Task {
    private final String by;

    public Deadlines(String description, boolean isCompleted, String by) {
        super(description, isCompleted);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String saveString() {
        return "D|" + super.saveString() + "|" + by;
    }
}

