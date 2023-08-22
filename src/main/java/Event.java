public class Event extends Task{

    private static String TYPE = "[E]";
    protected String fromTime;
    protected String toTime;

    public Event(String task, String fromTime, String toTime) {
        super(task);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        return this.TYPE + super.toString() + " (from: " + this.fromTime
                + " to: " + this.toTime + ")";
    }


}
