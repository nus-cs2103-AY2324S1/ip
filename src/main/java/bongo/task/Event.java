package bongo.task;

import java.time.LocalDateTime;

import bongo.helper.BongoException;
import bongo.helper.DateHelper;

public class Event extends Task {
    LocalDateTime from;
    LocalDateTime to;

    /**
     * A constructor for a Event.
     *
     * @param description Event description.
     * @param from        Event from datetime.
     * @param to          Event to datetime.
     * @throws BongoException If either Event from or to datetime is invalid.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) throws BongoException {
        super(description);
        this.validateEventDuration(from, to);
        this.from = from;
        this.to = to;
    }

    /**
     * A constructor for a Event, specifying whether it is done.
     *
     * @param description Event description.
     * @param isDone      Event marked done or undone.
     * @param from        Event from datetime.
     * @param to          Event to datetime.
     * @throws BongoException If either Event from or to datetime is invalid.
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
     *
     * @param from Event from datetime.
     * @param to   Event to datetime.
     * @throws BongoException If Event from/to datetime is in the past or if Event from datetime is after it's to datetime.
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
