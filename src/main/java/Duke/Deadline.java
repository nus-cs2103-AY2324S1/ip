package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to create and store deadlines.
 * Extends Task.
 */
public class Deadline extends Task{
    private LocalDate by;

    /**
     * public constructor for Deadline.
     * @param val
     * @param by
     */
    public Deadline (String val, String by){
        super(val);
        this.by=LocalDate.parse(by,DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
    public String toString(){
        return "[D]"+super.toString() +" (by: "+by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))+")";
    }
}
