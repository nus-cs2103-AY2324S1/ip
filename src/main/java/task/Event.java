package task;

public class Event extends Task {
    private String byTime;
    public Event(String details, String time) {
        super(details);
        super.setTaskType("E");
        byTime = time;
    }

    @Override
    public String toString() {
        String output;
        output = super.toString() + "(" + byTime + ")";
        return output;
    }
}
