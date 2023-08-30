import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate deadline;
    private static String invalidDate = "Please provide date with the following format: YYYY-MM-DD";

    Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    Deadline(String name, boolean isDone, LocalDate deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    boolean isToday(String dateStr) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(dateStr);
            return this.deadline.compareTo(date) == 0;
        } catch (DateTimeException e) {
            throw new DukeException(invalidDate);
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), 
            this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }

    @Override
    public String exportToText() {
        return String.format("deadline,>%s,>%s", super.exportToText(), this.deadline);
    }
}
