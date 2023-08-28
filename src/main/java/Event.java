public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.taskType = TaskType.EVENT;
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
        this.taskType = TaskType.EVENT;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " | to: " + to + ")";
    }

    @Override
    public String toTxt() {
        return super.toTxt() + this.description + " | " + this.from + " | " + this.to;
    }

}