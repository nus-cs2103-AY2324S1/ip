package ipduke;

public class Event extends Task {
    private String start;
    private String end;
    Event(String taskDetails) {
        super(taskDetails.split(" /from ")[0]);
        String timeline = taskDetails.split(" /from ")[1];
        this.start = timeline.split(" /to ")[0];
        this.end = timeline.split(" /to ")[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", this.start, this.end);
    }
}
