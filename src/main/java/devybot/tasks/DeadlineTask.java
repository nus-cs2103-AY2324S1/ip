package devybot.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    protected LocalDateTime dateTime;
    protected LocalDate date;

    public DeadlineTask(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public DeadlineTask(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public boolean isContaining(String keyword) {
        boolean superContains = super.isContaining(keyword);

        if (dateTime != null) {
            String formattedDateTime = formatDateTime(dateTime);
            return superContains || formattedDateTime.contains(keyword.toLowerCase());
        } else {
            String formattedDate = formatDate(date);
            return superContains || formattedDate.contains(keyword.toLowerCase());
        }
    }

    private String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")) + " " +
                dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a")).toLowerCase();
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("d/M/yyyy")) + " " +
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")).toLowerCase();
    }

    @Override
    public String toFileString() {
        if (dateTime != null) {
            return "D | " + super.toFileString() + " | "
                    + dateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } else {
            return "D | " + super.toFileString() + " | " + date.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
        }
    }

    @Override
    public String toString() {
        String formattedDate;

        if (dateTime != null) {
            formattedDate = dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"));
        } else {
            formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }

        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

}
