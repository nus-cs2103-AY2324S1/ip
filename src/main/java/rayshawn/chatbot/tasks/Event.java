package rayshawn.chatbot.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;

    public Event(String task, String date, String start, String end) {
        super(task, "E");
        this.date = LocalDate.parse(date);
        this.start = LocalTime.parse(start, DateTimeFormatter.ofPattern("ha"));
        this.end = LocalTime.parse(end, DateTimeFormatter.ofPattern("ha"));
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getStart() {
        return this.start.format(DateTimeFormatter.ofPattern("ha"));
    }

    public String getEnd() {
        return this.end.format(DateTimeFormatter.ofPattern("ha"));
    }

    @Override
    public String toString() {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h a");
        return super.toString() + " (from: " +
                this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                " " + this.start.format(timeFormat) + " to: " + this.end.format(timeFormat) + ")";

    }
}
