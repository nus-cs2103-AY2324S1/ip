package tasks;

public class Event extends TaskAbstract {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public void printStatus() {
        System.out.printf("[E][%s] %s (from: %s to: %s)\n", this.isDone ? "X" : " ", this.description, this.start, this.end);
    }
}
