import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + TimeParser.formatTime(this.start) + " to: " + TimeParser.formatTime(this.end) + ")";
    }
    public static Event interpret(String cmd) throws EmptyTaskException {
        Pattern pt = Pattern.compile("event(( (.*) )?/from( (.*) )?/to( (.*))?)?");
        Matcher mt = pt.matcher(cmd);

        mt.find();
        String overall = mt.group(1);
        String desc = mt.group(3);
        LocalDateTime start = TimeParser.parseTime(mt.group(5));
        LocalDateTime end = TimeParser.parseTime(mt.group(7));
        if (Task.checkEmpty(overall) || Task.checkEmpty(desc)) {
            throw new EmptyTaskException("Event");
        }
        return new Event(desc, start, end);

    }

    @Override
    public String getCmd() {
        return "event " +  super.description + " /from " + TimeParser.getCmd(this.start) + " /to "
                + TimeParser.getCmd(this.end);
    }
}
