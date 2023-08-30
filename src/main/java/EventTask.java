public class EventTask extends Task{
    public static String type = "E";
    private String from;
    private String to;
    public EventTask(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public EventTask(String name, boolean isDone, String from, String to) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (from: %s to: %s)",
                this.type,
                this.checkIsDone() ? "X" : " ",
                this.getName(),
                this.from,
                this.to);
    }
}
