class Deadline extends Task {
    protected String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    String stringToFile() {
        return String.format("D | %s | %s", super.stringToFile(), by);
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
