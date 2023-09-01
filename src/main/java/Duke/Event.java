package Duke.Task;

public class Event extends Task {
    private String from;
    private String to;
    public Event(String name, String from, String to, String input) {
        super(name, input);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        if (!this.getDone()) {
            return  "[E][ ] " + this.getName() + "(from: " + this.from + "to: " + this.to + ")";
        } else {
            return "[E][X] " + this.getName() + "(from: " + this.from + "to: " + this.to + ")";
        }
    }
}
