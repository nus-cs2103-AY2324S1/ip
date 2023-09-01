package mypackage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
  private final String to;
  private final String from;
  private LocalDate toDate;
  private LocalDate fromDate;

  public Event(String m) {
    super(m.substring(0, m.indexOf(" /from")));
    int indexOfFirstSlash = m.indexOf("/from ");
    int indexOfSecondSlash = m.indexOf("/to ", m.indexOf("/to "));
    this.from = m.substring(indexOfFirstSlash + 6, indexOfSecondSlash - 1);
    this.to = m.substring(indexOfSecondSlash + 4);
    setDate(to, true);
    setDate(from, false);
  }

  public Event (String m, String from, String to) {
    super(m);
    this.from = from;
    this.to = to;
    setDate(to, true);
    setDate(from, false);
  }

    // Method to set the date field if the incoming date is in the correct format
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
