package tasks;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class EventTask extends Task {

    protected final DateTimeFormatter INPUT_FORMAT
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected final DateTimeFormatter DISPLAY_FORMAT
            = DateTimeFormatter.ofPattern("dd-MMM-yyyy HHmm");

    protected LocalDateTime start;
    protected LocalDateTime end;

    public EventTask(String desc, LocalDateTime start, LocalDateTime end, int isMarked) {
        super(desc);
        this.start = start;
        this.end = end;
        if (isMarked == 1) {
            this.isDone = true;
        }
    }

    @Override
    protected String getTextFormattedString() {
        return String.format("E|%d|%s|%s|%s", this.isDone ? 1 : 0, this.desc,
                this.end.format(INPUT_FORMAT).toString(),
                this.start.format(INPUT_FORMAT).toString());
    }

    @Override
    public String toString() {
        String output = String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                this.start.format(DISPLAY_FORMAT).toString(),
                this.end.format(DISPLAY_FORMAT).toString());
        return output;
    }

}
