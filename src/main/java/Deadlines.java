import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Deadlines extends Task {
    private LocalDate by;
    public Deadlines(String task, String details, String done) throws DukeException {
        super(task, done);
        try {
            this.by = LocalDate.parse(details.substring(3));
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Please use the proper format for the deadline (YYYY-MM-DD).");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! There are missing details for the deadline.");
        }
    }

    public String printDetails() {
        return String.format("(by: %s)", this.by.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
    }
    
    @Override
    public String printTask() {
        return String.format("[D]%s%s", super.printTask(), this.printDetails());
    }

    public String addDetailsToStorage() {
        return String.format("| by %s", this.by);
    }
    @Override
    public String addToStorage() {
        return String.format("D %s%s%n", super.addToStorage(), this.addDetailsToStorage());
    }
}
