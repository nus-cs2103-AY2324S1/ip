package bongo.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import bongo.helper.BongoException;
import bongo.helper.DateHelper;

/**
 * A class for an Event.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

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
     * @throws BongoException If Event from/to datetime is in the past or if Event
     *                        from datetime is after it's to datetime.
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
    public boolean isTaskScheduledForDate(String date) {
        LocalDate fromDate = this.from.toLocalDate();
        LocalDate toDate = this.to.toLocalDate();
        LocalDate dateToCheck = LocalDate.parse(date, DateHelper.dateFormatter);
        return !(dateToCheck.isBefore(fromDate) || dateToCheck.isAfter(toDate));
    }

    @Override
    public String generateStringForTextFile() throws BongoException {
        String isTaskMarkedDone = this.isDone ? "1" : "0";
        return String.join(" | ", "E", isTaskMarkedDone, this.description,
                DateHelper.convertDateTimeToString(this.from), DateHelper.convertDateTimeToString(this.to));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)",
                super.generateDateTimeString(from), super.generateDateTimeString(to));
    }
}
