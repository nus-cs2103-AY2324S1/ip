import java.util.Date;
import java.text.SimpleDateFormat;

public class Deadline extends Task {

    protected Date by;

    public Deadline(String description, Date by) {
        super(description);
//        if (description.isEmpty())
//            throw new DukeException("☹ OOPS!!! The description / by of a deadline cannot be empty.");
        this.by = by;
    }

    @Override
    public String toWrite() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        return "D | " + super.toWrite() + " | " + formatter.format(by) + "\n";
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + formatter.format(by) + ")";
    }
}
