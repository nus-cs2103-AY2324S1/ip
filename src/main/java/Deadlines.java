public class Deadlines extends Task {
    protected String end;

    public Deadlines(String description, String end) {
        super(description);
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format(
                "| D |%s (by: %s)", super.toString(), end);
    }
}
