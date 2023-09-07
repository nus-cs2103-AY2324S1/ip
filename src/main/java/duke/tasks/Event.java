package duke.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected Date from;
    protected Date to;
    protected String fromFormat;
    protected String toFormat;

    public Event(String description, Date from , Date to) {
        super(description);
        this.from = from;
        this.to = to;
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy h a");
        this.fromFormat = outputFormat.format(from);
        this.toFormat = outputFormat.format(to);

    }

    public String addedMessage(){
        String ret = "";
        ret += "Got it. I've added this task:\n";
        ret += "  " + this + "\n";
        ret += "Now you have " + super.size + " tasks in the list.\n";

        return ret;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + fromFormat + " to: " + toFormat + ")";
    }
}