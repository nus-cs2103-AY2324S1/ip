package duke;

import duke.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected String transformedDate;

    public Deadline(String description, String by) {
        super(description);
        //assume user always input date first, only consider with or without time
        this.by = by;
        String parsed_by[] = by.split("\\s+");
        String byDate = LocalDate.parse(parsed_by[0]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.transformedDate = parsed_by.length > 1 ? byDate + " " + parsed_by[1] : byDate;
    }

    @Override
    public String toString() {
        String first = "[D]" + "[" + this.getStatusIcon() + "] " + this.description + " ";
        String second = "(by: " + this.transformedDate + ")";
        return first + second;
    }

    @Override
    public String stringInFile() {
        int status = super.isDone ? 1 : 0;
        return "D | " + status + " | " + this.description + "| " + this.by;
    }
}
