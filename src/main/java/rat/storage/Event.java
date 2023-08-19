package rat.storage;

public class Event extends Task {

    private String startTime;
    private String endTime;

    protected Event(String startTime, String endTime, String name) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String taskType = "[E]";
        return taskType + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

}
