import java.util.Scanner;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    public Event(int status, String desciption, String from, String to) {
        super(desciption, status != 0);     //if 0, return false, else return true
        this.from = from;
        this.to = to;
    }

    @Override
    public String storeToDisk() {
        return "E" + "|" + this.getStatus() + "|" + this.getDescription() + "|" + this.from + "|" + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

}
