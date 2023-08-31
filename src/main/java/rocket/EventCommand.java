package rocket;

import java.time.LocalDateTime;

public class EventCommand extends AddCommand{
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;


    public EventCommand (String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(new Event(description, isDone, from, to));
    }
}