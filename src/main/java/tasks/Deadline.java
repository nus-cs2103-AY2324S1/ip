package tasks;

import commands.Parser;
import exceptions.EmptyTaskException;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {

    protected LocalDateTime by;
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.formatTime(this.by) + ")";
    }
    public static Deadline interpret(String cmd) throws EmptyTaskException {
        Pattern pt = Pattern.compile("deadline(( (.*) )?/by( (.*))?)?");
        Matcher mt = pt.matcher(cmd);

        mt.find();
        // check if there is any text after deadline
        String overall = mt.group(1);
        // grab desc
        String desc = mt.group(3);
        // and the due date/time
        LocalDateTime due = Parser.parseTime(mt.group(5));
        if (Task.checkEmpty(overall)|| Task.checkEmpty(desc)) {
            throw new EmptyTaskException("tasks.Deadline");
        }
        return new Deadline(desc, due);
    }

    @Override
    public String getCmd() {
        return "deadline " + super.description + " /by " + Parser.getCmd(this.by);
    }
}
