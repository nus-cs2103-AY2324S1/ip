package Tasks;

public class EventTask extends Task {
    private String from = "";
    private String to = "";
    public EventTask(String itemName, String from, String to) {
        super(itemName);
        this.from = from;
        this.to = to;
    }


    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String encodeTask() {
        return super.encodeTask() + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
