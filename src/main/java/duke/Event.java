package duke;

import duke.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event extends Task{
    protected String from;
    protected String to;
    public Event (String description, String from, String to) {
        //no extra information for todolist
        super(description);
        String parsed_from[] = from.split("\\s+");
        String parsed_to[] = to.split("\\s+");
        //assume that date is always entered first
        //only consider with or without time
        String fromDate = LocalDate.parse(parsed_from[0]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String toDate = LocalDate.parse(parsed_to[0]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.from = parsed_from.length > 1 ? fromDate + " " + parsed_from[1] : fromDate;
        this.to = parsed_to.length > 1 ? toDate+ " " + parsed_to[1] : toDate;
    }

    @Override
    public String toString() {
        String first = "[E]" + "[" + this.getStatusIcon() + "] " + this.description + " ";
        String second = "(from: " + this.from + " to: " + this.to + ")";
        return first + second;
    }

    @Override
    public String stringInFile() {
        int status = super.isDone ? 1 : 0;
        return "E | " + status + " | " + this.description + "| " + this.from + "| " + this.to;
    }
}
