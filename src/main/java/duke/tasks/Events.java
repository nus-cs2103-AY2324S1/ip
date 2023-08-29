package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Events(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + from.format(DateTimeFormatter.ofPattern("dd MM yyyy HHmm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("dd MM yyyy HHmm")) + ")";
    }

    @Override
    public String makeFormat() {
        //E|1|descr|12/4/2020 1600|12/4/2020 1700
        return String.format("%s|%d|%s|%s|%s\n", "E", (isDone) ? 1 :0
                ,description, this.from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")),
                        this.to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
    }
}
