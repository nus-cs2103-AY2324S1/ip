public class Event extends Task {
    private String from;
    private String to;
    Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String getType() {
        return "event";
    }
    @Override
    public String saveTask() {
        String data = "E | ";
        if (this.isDone()) {
            data += "1 | ";
        } else {
            data += "0 | ";
        }
        data += this.getDescription();
        data = data + " | " + this.from + " | " + this.to + "\n";
        return data;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
