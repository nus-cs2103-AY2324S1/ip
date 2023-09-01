import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public LocalDate dateFrom;
    public LocalDate dateTo;


    public Event(String name, String from, String to) {
        super(name);
        this.dateFrom = LocalDate.parse(from, DateTimeFormatter.ofPattern("d MMM yyyy"));
        this.dateTo = LocalDate.parse(to, DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    @Override
    public String toString() {
        String dateFromString = this.dateFrom.format(DateTimeFormatter.ofPattern("dd.MMM.yyyy"));
        String dateToString = this.dateFrom.format(DateTimeFormatter.ofPattern("dd.MMM.yyyy"));
        return "[E]" + super.toString() + " (from: " + dateFromString + " to: " + dateToString + " )";
    }

    @Override
    public String taskToStringStore(Task task) {
        String dateFromString = this.dateFrom.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        String dateToString = this.dateFrom.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "E-" + super.taskToStringStore(task) + dateFromString + "-" + dateToString ;
    }
}