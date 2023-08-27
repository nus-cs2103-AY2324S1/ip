public class EventTask extends Task {

    protected String from;
    protected String to;
    public EventTask(String description, String from, String to) {
        super(description, false);
        this.from = from;
        this.to = to;
    }

    public EventTask(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public static EventTask parseEvent(String taskDetails) {
        if (!taskDetails.contains("from:") || !taskDetails.contains("to:")) {
            throw new RuntimeException("Error! Remember to include 'from:' and 'to:' after the event command!");
        }
        String[] details = taskDetails.split("from:", 2);
        String[] innerDetails = details[1].split("to:", 2);
        String description = details[0];
        String from = innerDetails[0];
        String to = innerDetails[1];
        return new EventTask(description, from, to);
    }

    public static EventTask parseEvent(String taskDetails, boolean isDone) {
        if (!taskDetails.contains("from:") || !taskDetails.contains("to:")) {
            throw new RuntimeException("Error! Remember to include 'from:' and 'to:' after the event command!");
        }
        String[] details = taskDetails.split("from:", 2);
        String[] innerDetails = details[1].split(" to:", 2);
        String description = details[0];
        description = description.replace(" ", "");
        String from = innerDetails[0];
        String to = innerDetails[1];
        return new EventTask(description, from, to, isDone);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.from + " to:" + this.to + ")";
    }
}
