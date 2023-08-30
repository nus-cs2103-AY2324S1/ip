package brotherman.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime startInDateTime;
    private LocalDateTime endInDateTime;


    public Event (String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startInDateTime = startTime;
        this.endInDateTime = endTime;

    }


    public String type() {
        return "E";
    }

    public String getStartTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = startInDateTime.format(formatter);
        return text;
    }

    public String getEndDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = endInDateTime.format(formatter);
        return text;
    }


    @Override
    public String storeText() {
        return String.format("%s|%s|%s/from%s/to%s", this.type(), this.isDone, this.description, getStartTime(), getEndDateTime());
    }

    @Override
    public String toString() {
        String type = this.type();
        return String.format("[%s]%s(from:%s to:%s)", type, super.toString(), getStartTime(), getEndDateTime() );
    }
}
