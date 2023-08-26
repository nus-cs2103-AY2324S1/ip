class Event extends Task {
    protected String from;
    protected String to;

    Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    String stringToFile() {
        return String.format("E | %s | %s | %s", super.stringToFile(), from, to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }

}
