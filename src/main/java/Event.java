import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) throws DukeException {
        super(description);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        if (!Parser.isValidDate(from, formatter) || !Parser.isValidDate(to, formatter)) {
            throw new InvalidDateTimeException();
        }
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    @Override
    public String toString() {
        return String.format("[E] [%s] %s (from: %s to: %s)",
                this.getStatusIcon(),
                super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }
}
