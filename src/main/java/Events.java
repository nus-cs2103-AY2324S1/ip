public class Events extends Task {
    private String from;
    private String to;
    public Events(String name, String from, String to) {
        super(name);
        this.to = to;
        this.from = from;
    }

    @Override
    public String toString() {
        return String.format("[E] %s %s (from: %s to: %s)", super.getMarking(), super.name, this.from, this.to);
    }
}
