package tasks;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class DeadlineTask extends Task {

    protected final DateTimeFormatter INPUT_FORMAT
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected final DateTimeFormatter DISPLAY_FORMAT
            = DateTimeFormatter.ofPattern("dd-MMM-yyyy HHmm");
    protected LocalDateTime deadline;

    public DeadlineTask(String desc, LocalDateTime deadline, int isMarked) {
        super(desc);
        this.deadline = deadline;
        if (isMarked == 1) {
            this.isDone = true;
        }
    }

    @Override
    protected String getTextFormattedString() {
        return String.format("D|%d|%s|%s", this.isDone ? 1 : 0,
                this.desc, this.deadline.format(INPUT_FORMAT).toString());
    }

    @Override
    public String toString() {
        String output = String.format("[D]%s (by: %s)",
                super.toString(),
                this.deadline.format(DISPLAY_FORMAT).toString());
        return output;
    }

}
