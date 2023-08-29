import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Events extends Task {

    private LocalDate from;
    private LocalDate to;
    
    public Events(String task, String from, String to) throws DukeException {
        super(task);

        // Throws error if format is incorrect.
        if (!from.startsWith("from") || !to.startsWith("to")) {
            throw new DukeException("☹ OOPS!!! Please use the proper format for the event.");
        }

        // Throws error if no 'from' and 'to' details are given.
        if (from.substring(5).isEmpty() || to.substring(3).isEmpty()) {
            throw new DukeException("☹ OOPS!!! There are missing details for the event.");
        }

        this.from = LocalDate.parse(from.substring(5));
        this.to = LocalDate.parse(to.substring(3));
    }
    
    public String printDetails() {
        return String.format("(from: %sto: %s)",
                this.from.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
    }

    @Override
    public String printTask() {
        return String.format("[E]%s%s", super.printTask(), this.printDetails());
    }

    public String addDetailsToStorage() {
        return String.format("| from %s| to %s", this.from, this.to);
    }
    @Override
    public String addToStorage() {
        return String.format("E %s%s%n", super.addToStorage(), this.addDetailsToStorage());
    }
}
