package tasks;

public class Event extends TaskAbstract {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String saveToTextFormat() {
        return String.format("E | %s | %s | %s - %s", this.isDone ? "1" : "0", this.description, this.start, this.end);
    }

    @Override
    public void printStatus() {
        System.out.printf("[E][%s] %s (from: %s to: %s)\n", this.isDone ? "X" : " ", this.description, this.start, this.end);
    }
}
