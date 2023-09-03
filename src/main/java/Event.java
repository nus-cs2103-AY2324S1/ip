public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event (String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public String writeTaskToFile() {
        return String.format("%s | %s | %s | %s-%s", "D",
                this.isDone() ? 1 : 0,
                this.getName(),
                this.getStartTime(),
                this.getEndTime());
    }

    public static Event readTaskFromFile(String[] args) {
        String[] eventTime = args[3].split("-");
        Event newEventTask = new Event(args[2], eventTime[0], eventTime[1]);
        if (args[1].equals("1")) {
            newEventTask.markDone();
        }
        return newEventTask;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.startTime, this.endTime);
    }
}

