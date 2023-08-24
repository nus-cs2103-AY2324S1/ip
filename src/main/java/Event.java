import java.util.regex.Matcher;

public class Event extends Task{
    private String startTime;
    private String endTime;

    Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    Event(Matcher matcher) {
        this(matcher.group("taskName"), matcher.group("startTime"), matcher.group("endTime"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
