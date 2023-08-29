package dot.tasks;

import java.time.LocalDateTime;

import dot.parser.Parser;

public class Deadline extends Task {

    private final Dateable deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = Dateable.of(deadline);
    }

    public Deadline(String description, String deadline, boolean isCompleted) {
        super(description, isCompleted);
        this.deadline = Dateable.of(deadline);
    }

    @Override
    public String getFileFormat() {
        return String.format("D | %s | %s", super.getFileFormat(),
                Parser.parseDisplayDatetimeToStorageDatetime(this.deadline.toString()));
    }

    @Override
    public boolean isOnDate(LocalDateTime startOfDay, LocalDateTime endOfDay) {
        // Event can either start or end on the date itself, or both
        return this.deadline.isAfterOrOn(startOfDay) && this.deadline.isBeforeOrOn(endOfDay);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", this.deadline);
    }

}
