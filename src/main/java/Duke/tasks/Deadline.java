package Duke.tasks;

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
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this);
        System.out.println("Now you have " + super.size + " Duke.tasks in the list.");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + formatDate + ")";
    }
}