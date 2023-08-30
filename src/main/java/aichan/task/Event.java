package aichan.task;

public class Event extends Task {
    private String start;
    private String end;
    public Event(String[] strs) {
        // inside this array has 3 elements
        // first is taskName, second element is start, third element is end time
        super(strs[0]);
        this.start = strs[1];
        this.end = strs[2];
    }

    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start, this.end);
    }
}
