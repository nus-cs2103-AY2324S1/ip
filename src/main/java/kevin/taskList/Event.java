package kevin.taskList;

public class Event extends Task{
    private final String startTime;
    private final String endTime;

    public Event(Boolean isDone, String name, String startTime, String endTime) {
        super(isDone, name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toText() {
        return "Event - "  + isDone + " - " + name + " -" + startTime + " -" + endTime + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + this.startTime + " to:" + this.endTime + ")";
    }
}
