package chatbot;

/**
  * Stores the task details for events.
  */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor for Event. Initialises the description + extras.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String getExtras() {
        return "(from:" + from + " to:" + to + ")";
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.getStatusIcon() + 
                    super.description + getExtras();
    }
}