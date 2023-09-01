package rocket;

import java.time.LocalDateTime;

public class EventCommand extends AddCommand{
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;


    /**
     * Creates event command.
     * @param description event description.
     * @param isDone whether the event is over.
     * @param from start date.
     * @param to end date.
     */
    public EventCommand (String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(new Event(description, isDone, from, to));
    }
}