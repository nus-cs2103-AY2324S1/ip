public class Event extends Task {
    private String from;
    private String to;
    public Event(String d, String from, String to) {
        super(d);
        this.from = from;
        this.to = to;
    }

    public Event(String d, String from, String to, boolean completed) {
        super(d);
        this.from = from;
        this.to = to;
        this.completed = completed;
    }

    @Override
    public String toString() {
        String marker = "[ ]";
        if (this.completed) marker = "[X]";
        return  "[E]" + marker + " " +
                this.description +
                "(from: " + this.from +
                "to: " + this.to + ")\n";
    }

    @Override
    public String writeToFile() {
        int mark = completed ? 1 : 0;
        String data = 3 + " " + mark + description + "/" + from +
                "/" + to + System.lineSeparator();
        return data;
    }
}
