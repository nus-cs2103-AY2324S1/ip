public class Event extends Task {
    private String type = "E";
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + " (at: " + at + ")" ;
    }
}
