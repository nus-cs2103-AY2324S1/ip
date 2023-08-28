import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    private LocalDateTime from, to;
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description); // initializes its task
        this.from = from;
        this.to = to;
    }

    public String changeFormat() {
        int isDone = 0;
        if (isMarked) {
            isDone = 1;
        }

        String formattedDateFrom = from.format(DateTimeFormatter.ofPattern("dd MMM HH:mm"));
        String formattedDateTo = to.format(DateTimeFormatter.ofPattern("dd MMM HH:mm"));

        return "E" + " | " + isDone + " | " + super.description + " | " + formattedDateFrom + " | " + formattedDateTo;
    };

    public LocalDateTime getFromDate() {
        return this.from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
                + " to:" + to.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) +")";

    }
}