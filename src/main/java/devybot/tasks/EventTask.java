package devybot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isContaining(String keyword) {
        boolean superContains = super.isContaining(keyword);
        String formattedFromDateTime = formatDateTime(from);
        String formattedToDateTime = formatDateTime(to);

        return superContains || formattedFromDateTime.contains(keyword.toLowerCase())
                || formattedToDateTime.contains(keyword.toLowerCase());
    }

    private String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")) + " " +
                dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a")).toLowerCase();
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + from.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                + " | " + to.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    @Override
    public String toString() {
        String formattedFrom = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        String formattedTo = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }
}
