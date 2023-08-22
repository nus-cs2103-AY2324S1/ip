public class Event extends Task {
    private String start;
    private String end;

    public Event(String title, String start, String end) {
        super(title, false);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        if (this.done == true) {
            return "[E] " + "[X] " + this.title;
        }
        return "[E] " + "[ ] " + this.title;
    }
}

