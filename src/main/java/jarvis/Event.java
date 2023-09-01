package jarvis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;
    
    public Event(String title, LocalDateTime fromDateTime, LocalDateTime toDateTime, boolean isCompleted) {
        super(title, isCompleted);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Ui.DATE_TIME_FORMAT);
        String formattedFromDateTime = fromDateTime.format(formatter);
        String formattedToDateTime = toDateTime.format(formatter);
        return "E | " + super.toString() + " | " + formattedFromDateTime + " | " + formattedToDateTime;
    }
}
