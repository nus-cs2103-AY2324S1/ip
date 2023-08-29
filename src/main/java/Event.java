import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
class Event extends Task{
    private final LocalDate timeFrom;
    private final LocalDate timeTo;

    public Event(String name, LocalDate timeFrom, LocalDate timeTo) {
        super(name);
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }


    @Override
    public String getText() {
        return super.getText() + " | " + timeFrom + " | " + timeTo;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: "
                + timeFrom.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "
                + timeTo.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
