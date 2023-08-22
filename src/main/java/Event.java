public class Event extends Task {
    private String start;
    private String end;

    public Event(String description) {
        super(description.split(" /from ")[0]);
        this.start = description.split(" /from ")[1].split(" /to ")[0];
        this.end = description.split(" /from ")[1].split(" /to ")[1];
    }

    @Override
    public String toString() {
        String done = this.done ? "[X]" : "[ ]";
        return "[E]" + done + " " + this.name
                + " (from: " + this.start + " to: " + this.end + ")";
    }
}
