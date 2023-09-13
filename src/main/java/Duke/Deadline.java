package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to create and store deadlines.
 * Extends Task.
 */
public class Deadline extends Task implements Comparable{
    private LocalDate by;

    /**
     * public constructor for Deadline.
     * @param val
     * @param by
     */
    public Deadline (String val, String by){
        super(val);
        assert !val.isEmpty() && !by.isEmpty();
        assert by.matches("\\w{3} \\d{2} \\d{4}");
        this.by=LocalDate.parse(by,DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
    public String toString(){
        return "[D]"+super.toString() +" (by: "+by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))+")";
    }

    @Override
    public int compareTo(Object o) {
        Deadline d= (Deadline) o;
        return this.by.compareTo(d.by);
    }
}
