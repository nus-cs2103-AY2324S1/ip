public class Event extends Task {
    String startTime;
    String endTime;
    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[E][X] " + this.name +
                    " (from: " + this.startTime + " to: " + this.endTime + ")";
        }

        return "[E][ ] " + this.name +
                " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
