package data.tasks;

import java.time.LocalDateTime;

import common.DateParser;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(
            String detail,
            LocalDateTime deadline) {
        super(detail, deadline);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format(
            "[D]%s (by: %s)",
            super.toString(),
            DateParser.toDisplayString(this.deadline)
        );
    }

    @Override
    public String toFileFormatString() {
        return String.format(
            "D|%s|%s",
            super.toFileFormatString(),
            DateParser.toFileString(this.deadline)
        );
    }
}
