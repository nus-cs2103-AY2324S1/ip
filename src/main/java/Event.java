import java.time.LocalDate;

public class Event extends Task {
    private LocalDate fromDate, toDate;
    public Event(String item, LocalDate fromDate, LocalDate toDate) {
        super(item);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    public Event(String item, LocalDate fromDate, LocalDate toDate, boolean done) {
        super(item, done);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDate.toString() + " to: " + toDate.toString() +")";
    }
    @Override
    public String saveItem() {
        return "E | " + super.saveItem() +
                "from: " + fromDate.toString() + "to: " + toDate.toString();
    }
}