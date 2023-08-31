package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime deadline ;

    public Deadline(String name, LocalDateTime deadline){
        super(name);
        this.deadline = deadline;
    }

    // Override toString method
    @Override public String toString(){
        return "[D] " + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy ha")) + ")";
    }

}
