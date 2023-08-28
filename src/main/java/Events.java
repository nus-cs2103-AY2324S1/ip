public class Events extends Task{

    private String start;
    private String end;

    Events(String start, String end, String description) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString().substring(3) + " (from: " + this.start + " to: " + this.end + ")";
    }

    public String type() {
        return "E";
    }
    public String getDate() {
        return this.start + "-" + this.end;
    }
}
