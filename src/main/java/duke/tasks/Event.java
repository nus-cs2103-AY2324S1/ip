package duke.tasks;

public class Event extends Task {
    private static final String PRINT_FORMAT = "[E]%s %s (from: %s to: %s)";
    private static final String STORE_FORMAT = "[E] | %s %s | %s | %s";
    private String from;
    private String to;

    public Event(String description, String from, String to) {

        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String saveString() {
        return String.format(STORE_FORMAT, getFlag(), this.getDescription().trim(), from, to);
    }

    @Override
    public String toString() {
        return String.format(PRINT_FORMAT, getFlag(), this.getDescription(), from, to);

    }
}


