import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {
    private LocalDate from;
    private LocalDate to;
    Events(String name, String from, String to, boolean isDone) throws DateTimeParseException {
        super(name, isDone);
        this.from = LocalDate.parse(from.trim());
        this.to = LocalDate.parse(to.trim());
    }

    /*
    Method name: toString
    Description: Prints the task name and whether it is done
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + "(from : " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /*
Method name: markDone
Description: Checks the specified task
 */
    @Override
    public void markDone() {
        super.markDone();
        System.out.println("Oki, I've marked this task as done: \n" + this.toString());
    }

    /*
    Method name: unmarkDone
    Description: Unchecks the specified task
     */
    @Override
    public void unmarkDone() {
        super.unmarkDone();
        System.out.println("Damn bro...unmarked this task :( : \n" + this.toString());
    }
}
