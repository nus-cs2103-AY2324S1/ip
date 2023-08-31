import java.time.LocalDate;
import java.time.DateTimeException;
public class Deadline extends Task {

    public Deadline(String input) {
        super(input.split("/")[0]);
        try {
            setDate(input.split("/")[1].replace("by ", ""));
        } catch (NullPointerException e) {
            throw new KieraException("write a date for your deadline in the form: /by yyyy-mm-dd 2359");
        } catch (DateTimeException e) {
            throw new KieraException("fill in the date in this format: /by yyyy-mm-dd!");
        }
    }
    @Override
    public String toStorageString() {
        return "D // " + this.getStatusIcon() + " // " + this.getDescription() + " /by " + this.getDate();
    }
    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + this.printDate() + ")";
    }
}
