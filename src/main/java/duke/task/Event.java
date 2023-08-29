package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    String[] inputs;
    LocalDate startDate;
    LocalDate endDate;
    public Event(String name) throws DukeException {
        super(name.split("/")[0]);
        this.ogname = name;
        this.type = "Event";

        this.inputs = name.split("/");
        if (this.inputs.length < 3) {
            throw new DukeException(" Event has no end date!");
        }
        int fromStart = name.indexOf("/from");
        int toStart = name.indexOf("/to");
        String sDate = (name.substring(fromStart, toStart - 1)).substring(6);
        String eDate = (name.substring(toStart)).substring(4);

        this.startDate = LocalDate.parse(sDate);
        this.endDate = LocalDate.parse(eDate);

        if (this.startDate.compareTo(this.endDate) > 0) {
            throw new DukeException(" From is later than To!");
        }

    }

    @Override
    public String toString() {
        String fromDate = startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String toDate = endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));


        return "[E]" + super.toString() + "(from: " + fromDate  + " to: " + toDate + ")";
    }
}
