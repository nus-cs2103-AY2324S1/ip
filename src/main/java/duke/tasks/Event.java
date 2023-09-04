package duke.tasks;

public class Event extends Task {
    private static final String DONE_FLAG = "[X] ";
    private static final String UNDONE_FLAG = "[ ] ";
    private static final String PRINT_FORMAT = "[E]%s %s (from: %s to: %s)";
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String status = this.isDone() ? DONE_FLAG : UNDONE_FLAG;
        return String.format(PRINT_FORMAT, status, this.getDescription(), from, to);
    }
}


