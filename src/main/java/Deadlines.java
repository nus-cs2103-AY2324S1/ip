import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task {
    private LocalDate by;

    Deadlines(String name, String by, boolean isDone) throws DateTimeParseException {
        super(name, isDone);
        this.by = LocalDate.parse(by.trim());
    }

    /*
    Method name: toString
    Description: Prints the task name and whether it is done
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by : " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
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
