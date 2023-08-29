public class Events extends Task{
    private final String from;
    private final String to;

    public Events(String description, boolean isCompleted, String from, String to) {
        super(description, isCompleted);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String saveString() {
        return "E|" + super.saveString() + "|" + from + "|" + to;
    }
}
