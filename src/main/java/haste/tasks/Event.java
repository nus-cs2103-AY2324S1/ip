package tasks;

import commands.Parser;
import exceptions.EmptyTaskException;

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
        return "[E]" + super.toString() + " (from: " + Parser.formatTime(this.start) + " to: " + Parser.formatTime(this.end) + ")";
    }
    public static Event interpret(String cmd) throws EmptyTaskException {
        Pattern pt = Pattern.compile("event(( (.*) )?/from( (.*) )?/to( (.*))?)?");
        Matcher mt = pt.matcher(cmd);
        mt.find();
        String overall = mt.group(1);
        String desc = mt.group(3);
        LocalDateTime start = Parser.parseTime(mt.group(5));
        LocalDateTime end = Parser.parseTime(mt.group(7));
        if (Task.checkEmpty(overall) || Task.checkEmpty(desc)) {
            throw new EmptyTaskException("tasks.Event");
        }
        return new Event(desc, start, end);

    }

    @Override
    public String getCmd() {
        return "event " +  super.description + " /from " + Parser.getCmd(this.start) + " /to "
                + Parser.getCmd(this.end);
    }
}
