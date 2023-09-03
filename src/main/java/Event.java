public class Event extends Task {
    private String start;
    private String end;

    public Event(int status, String task, String start, String end) {
        super(status, task);
        this.start = start;
        this.end = end;
    }

    @Override
    public String convertTask() {
        return "E | " + super.getStatus() + " | " + super.getTask() +
                " | " + start + " | " + end;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
