import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    private LocalDateTime from, to;
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isMarked) {
        super(description); // initializes its task
        this.from = from;
        this.to = to;
        super.isMarked = isMarked;
    }

    public String writeFormat() {
        int isDone = 0;
        if (isMarked) {
            isDone = 1;
        }
//        String formattedDateFrom = from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        String formattedDateTo = to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String formattedDateFrom = from.format(super.timeFormat);
        String formattedDateTo = to.format(super.timeFormat);
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