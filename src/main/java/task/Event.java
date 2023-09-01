package task;

public class Event extends Task {

    private static final String TASK_HEADER = "[E] ";
    private final String startTime;
    private final String endTime;

    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String bool, String name, String startTime, String endTime) {
        super(name, Boolean.parseBoolean(bool));
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String fileWriteFormatted() {
        return Event.TASK_HEADER + Task.UNIQUE_FILE_SEPARATOR +
                super.fileWriteFormatted() + Task.UNIQUE_FILE_SEPARATOR + this.startTime +
                Task.UNIQUE_FILE_SEPARATOR + this.endTime;
    }

    @Override
    public String toString() {
        return Event.TASK_HEADER + super.toString()+
                " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
