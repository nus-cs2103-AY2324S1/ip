package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event subclass inherits from Task and contains information about
 * its start and stop date
 */
public class Event extends Task {

    /**
     * field inputs to split the name of the command given
     * field startDate stores the from date parsed into the Event
     * field endDate stores the to date parsed into the Event
     */
    private final String[] inputs;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * constructor for the Event class
     * @param name name of the event
     * @throws DukeException error if the dates are placed wrongly
     */
    public Event(String name) throws DukeException {
        super(name.split("/")[0]);
        this.ogName = name;
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

    /**
     * @return String representation of the Event class
     */
    @Override
    public String toString() {
        String fromDate = startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String toDate = endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + "(from: " + fromDate + " to: " + toDate + ")";
    }
}
