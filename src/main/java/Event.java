class Event extends Task {
    private String from, to;
    public Event(String description, String from, String to) {
        super(description); // initializes its task
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from + "to:" + to +")";
    }
}