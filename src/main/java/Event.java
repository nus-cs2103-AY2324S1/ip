import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//this class represents an event
public class Event extends Task{
    LocalDate start;
    LocalDate end;
    //default constructor to store start and end
    public Event(String name, String start, String end) {
        super(name);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            this.start = LocalDate.parse(start, inputFormatter);
            this.end=LocalDate.parse(end, inputFormatter);
        } catch (Exception e) {
            System.out.println("Invalid date!");
        }
    }

    //default display to represent the event object
    public String display() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        if(done) {
            return "[E][X] " + this.name + " (From: " + start.format(outputFormatter) + " To: " + end.format(outputFormatter) + ")";
        }
        return "[E][] " + this.name + " (From: " + start.format(outputFormatter) + " To: " + end.format(outputFormatter) + ")";
    }
}