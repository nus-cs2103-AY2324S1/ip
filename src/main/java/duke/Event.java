package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task{
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end){
        super(description);
        this.start = start;
        this.end = end;
        this.tag = Tag.E;
    }

    public String changeFormat(LocalDateTime date){
        return date.getMonth().toString() + " " + date.getDayOfMonth() + " " +
                date.getYear() + ", " + date.toLocalTime();
    }

    @Override
    public String toString(){
        return String.format("%s (from: %s to: %s)", description, changeFormat(start),
                changeFormat(end));
    }

}
