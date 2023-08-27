public class Event extends Task{
    private String start;
    private String end;
    public Event(String task, boolean isDone, String start, String end) {
        super(task, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toSaveFormat() {
        return "E|" + super.toSaveFormat() + String.format("|%s|%s\n", start, end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format("(from: %sto: %s)", start, end);
    }
}
