package tasks;

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

    /**
     * Returns a string that represents the Event
     *
     * @return string with details of the Event
     */
    @Override
    public String toString() {
        String dateFromString = this.dateFrom.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String dateToString = this.dateTo.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return "[E]" + super.toString() + " (from: " + dateFromString + " to: " + dateToString + ")";
    }

    /**
     * Returns a string that represents the Event to be stored in txt file
     *
     * @return a formatted string with details of the Event
     */
    @Override
    public String taskToStringStore(Task task) {
        String dateFromString = this.dateFrom.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        String dateToString = this.dateTo.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "E-" + super.taskToStringStore(task) + dateFromString + "-" + dateToString ;
    }
}