public class Event extends Task{
    protected String from;
    protected String till;

    public Event(String description, String from, String till) {
        super(description);
        this.from = from;
        this.till = till;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + from + " to: " + till + ")";
    }
}
