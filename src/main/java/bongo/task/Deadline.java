package bongo.task;

import java.time.LocalDateTime;

import bongo.helper.BongoException;
import bongo.helper.DateHelper;

public class Deadline extends Task {
    LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) throws BongoException {
        super(description);
        this.validateDeadline(deadline);
        this.deadline = deadline;
    }

    public Deadline(String description, boolean isDone, LocalDateTime deadline) throws BongoException {
        super(description);
        this.isDone = isDone;
        this.validateDeadline(deadline);
        this.deadline = deadline;
    }

    private void validateDeadline(LocalDateTime deadline) throws BongoException {
        if (deadline.isBefore(LocalDateTime.now())) {
            throw new BongoException("Deadline must be in the future.");
        }
    }

    @Override
    public String generateStringForTextFile() {
        String isTaskMarkedDone = this.isDone ? "1" : "0";
        return String.join(" | ", "D", isTaskMarkedDone, this.description, DateHelper.formatter.format(this.deadline));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", super.generateDateString(this.deadline));
    }
}
