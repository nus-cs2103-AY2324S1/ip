package duck.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contains the details of events as a part of task.
 */
public class Events extends Task {

    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs the Events Object.
     * @param start Start date and time of the event.
     * @param end end date and time of the event.
     * @param description Contains the description of the event.
     */
    public Events(LocalDateTime start, LocalDateTime end, String description) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String getExactDate() {
        String mmmDdYyyy = "";
        if (start.toLocalDate().equals(end.toLocalDate())) {
            mmmDdYyyy = getMmmDdYyyy();
        } else {
            mmmDdYyyy = start.format(DateTimeFormatter.ofPattern("hh:mm a MMM dd yyyy")) + " to: "
                + end.format(DateTimeFormatter.ofPattern("hh:mm a MMM dd yyyy"));
        }
        return mmmDdYyyy;

    }

    private String getMmmDdYyyy() {
        String mmmDdYyyy;
        mmmDdYyyy = start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
            + start.format(DateTimeFormatter.ofPattern("hh:mm a")) + " to: "
            + end.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return mmmDdYyyy;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString().substring(3) + " (from: " + getExactDate()
            + ")";
    }

    public String type() {
        return "E";
    }

    public LocalDate getRawDate() {
        return this.start.toLocalDate();
    }

    public String getDate() {
        return this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + "-"
            + this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }
}
