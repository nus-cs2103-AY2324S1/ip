import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    protected String start;
    protected String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
    public static Event interpret(String cmd) throws EmptyTaskException {
        Pattern pt = Pattern.compile("event(( (.*) )?/from( (.*) )?/to( (.*))?)?");
        Matcher mt = pt.matcher(cmd);

        mt.find();
        String overall = mt.group(1);
        String desc = mt.group(3);
        String start = mt.group(5);
        String end = mt.group(7);
        if (Task.checkEmpty(overall) || Task.checkEmpty(desc) || Task.checkEmpty(start) || Task.checkEmpty(end)) {
            throw new EmptyTaskException("Event");
        }
        return new Event(desc, start, end);

    }

    @Override
    public String getCmd() {
        return "event " +  super.description + " /from " + this.start + " /to " + this.end;
    }
}
