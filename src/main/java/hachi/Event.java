package hachi;

import exceptions.DateWrongOrderException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;
    public Event(String taskName, LocalDate startDate, LocalDate endDate) throws DateWrongOrderException {
        super(taskName);
        if (startDate.isAfter(endDate)) {
            throw new DateWrongOrderException(startDate, endDate);
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Checks if supplied date is during the event
     *
     * @return true if given date is during the event, false otherwise
     */
    public boolean isDateWithinRange(LocalDate date) {
        return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
    }

    /**
     * Converts hachi.Event object to its string representation when stored in the hard drive.
     *
     * @return String representation when stored in text file on user's hard drive
     */
    @Override
    public String toData() {
        return "E" + " | " + super.toData() + " | " + startDate.format(DateTimeFormatter.ISO_DATE)
                + " | " + endDate.format(DateTimeFormatter.ISO_DATE);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}