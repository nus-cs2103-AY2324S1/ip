package duke.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected Date by;
    protected String formatDate;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy h a");
        this.formatDate = outputFormat.format(by);
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
        return "[D]" + super.toString() + "(by: " + formatDate + ")";
    }
}