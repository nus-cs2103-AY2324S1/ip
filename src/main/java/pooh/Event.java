package pooh;

public class Event extends Task {
    private final String eventStartTime;
    private final String eventEndTime;

    public Event(String description, String eventStartTime, String eventEndTime) {
        super(description);
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    public String writeTaskToFile() {
        return String.format("%s | %s | %s | %s-%s", "E",
                this.getIsDone() ? 1 : 0,
                this.getDescription(),
                this.eventStartTime,
                this.eventEndTime);
    }

    public static Event readTaskFromFile(String[] args) {
        String[] eventTime = args[3].split("-");
        Event newEventTask = new Event(args[2], eventTime[0], eventTime[1]);
        if (args[1].equals("1")) {
            newEventTask.markAsDone();
        }
        return newEventTask;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.eventStartTime, this.eventEndTime);
    }
}
