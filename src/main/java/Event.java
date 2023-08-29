public class Event extends Task{

    private String TaskIcon = "[E]";
    private String startDate;
    private String endDate;

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String description, String startDate, String endDate, boolean isDone) {
        // For Load
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String getTaskAsString() {
        String message = String.format("%s[%s] %s (from: %s to: %s)", this.TaskIcon,this.getStatusIcon(), this.getDescription(), this.startDate, this.endDate);
        return message;
    }
}
