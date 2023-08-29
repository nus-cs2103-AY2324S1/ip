import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
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
        String due = mt.group(5);
        if (Task.checkEmpty(overall)|| Task.checkEmpty(desc) || Task.checkEmpty(due)) {
            throw new EmptyTaskException("Deadline");
        }
        return new Deadline(desc, due);
    }

    @Override
    public String getCmd() {
        return "deadline " + super.description + " /by " + this.by;
    }
}
