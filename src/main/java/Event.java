public class Event extends Task {
    private String startTime;
    private String endTime;

    private Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String description, String startTime, String endTime, boolean isDone) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDone = isDone;
    }

    public static Event createNewEventTask(String description) {
        String[] splitMessage1 = description.split(" /from ");
        String[] splitMessage2 = splitMessage1[1].split(" /to ");
        return new Event(splitMessage1[0], splitMessage2[0], splitMessage2[1]);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
