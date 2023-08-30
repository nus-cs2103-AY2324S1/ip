package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start ;
    protected LocalDateTime end ;

        public Event(String name, LocalDateTime start, LocalDateTime end){
        super(name);
        this.start = start;
        this.end = end;
    }

    //toString method
    @Override public String toString(){
        return "[E] " + super.toString() + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy ha")) +
                " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy ha")) + ")";
    }

}
