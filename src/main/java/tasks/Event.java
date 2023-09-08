package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final String to;
    private final String from;
    private LocalDate toDate;
    private LocalDate fromDate;
    /**
     * Constructor for Event class.
     * @param m String containing the description of the events
     */
    public Event(String m) {
        super(m.substring(0, m.indexOf(" /from")));
        int indexOfFirstSlash = m.indexOf("/from ");
        int indexOfSecondSlash = m.indexOf("/to ", m.indexOf("/to "));
        this.from = m.substring(indexOfFirstSlash + 6, indexOfSecondSlash - 1);
        this.to = m.substring(indexOfSecondSlash + 4);
        setDate(to, true);
        setDate(from, false);
    }

    /**
     * Constructor for Event class.
     * @param m String containing the description of the event.
     * @param from String containing the starting date of the event.
     * @param to String containing the ending date of the event.
     */
    public Event (String m, String from, String to) {
      super(m);
      this.from = from;
      this.to = to;
      setDate(to, true);
      setDate(from, false);
    }

    /**
     * Sets the date of the event.
     * @param by String containing the date of the event.
     * @param isToDate Boolean value to check if the date is a proper date yyyy-MM-dd string.
     */
    private void setDate(String by, boolean isToDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if (isToDate) {
                toDate = LocalDate.parse(by, formatter);
            } else {
              fromDate = LocalDate.parse(by, formatter);
            }
        } catch (DateTimeParseException e) {
            if (isToDate) {
                this.toDate = null;
            } else {
                this.fromDate = null;
            }
        }
    }

    /**
     * Returns the date of the event.
     * @param date LocalDate object containing the date of the event.
     * @return String containing the date of the event.
     */
    private String getDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

  
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                            fromDate != null ? getDate(fromDate) : from,
                            toDate != null ? getDate(toDate) : to
                            );
    }
}
