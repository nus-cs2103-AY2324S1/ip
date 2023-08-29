package task;

public class Event extends Task {
    private String byTime;
    public Event(String details, String time) {
        super(details);
        super.setTaskType(TaskType.E.toString());
        byTime = time;
    }

    @Override
    public String saveFormat() {
        String output;
        output = super.saveFormat() + "/ " + byTime;
        return output;
    }

    @Override
    public String toString() {
        String output;
        output = super.toString() + "(" + byTime + ")";
        return output;
    }
}
