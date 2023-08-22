package duke.utils;

public class Event extends Task {
    private final String start;
    private final String end;
    public Event (String description) {
        super();

        String[] tokens = description.split("/");
        this.description = tokens[0];
        this.start = tokens[1].substring(5);
        this.end = tokens[2].substring(3);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + "to: "+ this.end + ")";
    }
}
