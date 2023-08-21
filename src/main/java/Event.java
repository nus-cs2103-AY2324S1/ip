public class Event extends Task{

    private String startTime;
    private String endTime;
    public static Event create(String rawLine) {
        String[] instructions = rawLine.split(" /from ");
        String[] timeLine = instructions[1].split(" to: ");
        return new Event(instructions[0], timeLine[0], timeLine[1]);
    }
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format(
                "[E][%s] %s (from: %s to: %s)",
                super.getStatusIcon(),
                super.description,
                this.startTime,
                this.endTime
        );
    }
}
