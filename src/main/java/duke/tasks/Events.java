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
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Events)) {
            return false;
        }
        Events t = (Events) o;
        return t.description.equals(this.description) && t.from.equals(this.from)
                && t.to.equals(this.to);
    }



    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }

    @Override
    public String makeFormat() {
        return String.format("%s|%d|%s|%s|%s\n", "E", (isDone) ? 1 :0
                ,description, this.from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")),
                        this.to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
    }
}
