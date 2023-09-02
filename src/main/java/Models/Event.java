package Models;

public class Event extends Task {
    String startTime;
    String endTime;
    public Event(String name, Boolean marked, String startTime, String endTime) {
        super(name, marked);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getTaskDetails() {
        return "Event," + this.name + "," + this.isMarked + "," + this.startTime + "," + this.endTime;
    }

    @Override
    public String toString() {
        if (this.isMarked) {
            return "[E][X] " + this.name +
                    " (from: " + this.startTime + " to: " + this.endTime + ")";
        }

        return "[E][ ] " + this.name +
                " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
