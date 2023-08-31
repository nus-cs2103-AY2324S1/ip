package bongo.task;

import java.time.LocalDateTime;

import bongo.helper.BongoException;
import bongo.helper.DateHelper;

public class Event extends Task {
    LocalDateTime from;
    LocalDateTime to;

    /**
     * A constructor for a Event.
     * @param description
     * @param from
     * @param to
     * @throws BongoException
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) throws BongoException {
        super(description);
        this.validateEventDuration(from, to);
        this.from = from;
        this.to = to;
    }

    /**
     * A constructor for a Event, specifying whether it is done.
     * @param description
     * @param isDone
     * @param from
     * @param to
     * @throws BongoException
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) throws BongoException {
        super(description);
        this.isDone = isDone;
        this.validateEventDuration(from, to);
        this.from = from;
        this.to = to;
    }

    /**
     * Check if event to and from datetime provided is valid.
     * @param from
     * @param to
     * @throws BongoException
     */
    private void validateEventDuration(LocalDateTime from, LocalDateTime to) throws BongoException {
        if (from.isBefore(LocalDateTime.now()) || to.isBefore(LocalDateTime.now())) {
            throw new BongoException("Start and end of event must be in the future.");
        }
        if (from.isAfter(to)) {
            throw new BongoException("End of event must be after start of event.");
        }
    }

    @Override
    public String generateStringForTextFile() {
        String isTaskMarkedDone = this.isDone ? "1" : "0";
        return String.join(" | ", "E", isTaskMarkedDone, this.description, DateHelper.formatter.format(this.from), DateHelper.formatter.format(this.to));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", super.generateDateString(from), super.generateDateString(to));
    }
}
