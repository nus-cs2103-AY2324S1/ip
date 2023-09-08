package prts.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a start and end date.
 * This date can be recognized if input in the format Day-Month-Year, with short Month forms.
 * If a date cannot be parsed, then the string input is stored verbatim as the start/end date.
 */
public class Event extends Task {

    private String description1;
    private String description2;
    private final String addMessage = "Roger that. Preparations will be underway.";

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructs an Event object, with user-input strings as the start and end dates.
     * @param name The description of the Event.
     * @param description1 The start date of the Event.
     * @param description2 The end date of the Event.
     */
    public Event(String name, String description1, String description2) {
        super(name);
        this.description1 = description1;
        this.description2 = description2;
    }

    /**
     * Constructs an Event object, given dates parsed by the CommandParser.
     * @param name The description of the Event.
     * @param startDate The start date of the Event.
     * @param endDate The end date of the Event.
     */
    public Event(String name, LocalDateTime startDate, LocalDateTime endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the string representation of this Event object, for display to the user.
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        String detail1 = description1 == null
                ? startDate.format(DateTimeFormatter.ofPattern("hh':'mma',' d MMM uuuu',' eee"))
                : description1;
        String detail2 = description2 == null
                ? endDate.format(DateTimeFormatter.ofPattern("hh':'mma',' d MMM uuuu',' eee"))
                : description2;
        return "[E]" + super.toString() + " (from: " + detail1 + " to: " + detail2 + ")";
    }

    /**
     * Returns the message displayed to the user upon addition of an Event to the TaskList.
     * @return The message displayed to the user.
     */
    public String getAddMessage() {
        return addMessage;
    }

    /**
     * Searches for a given search term in the name or the description strings.
     * If there is no description string due to the Event being constructed with Dates, then
     * only the name is searched.
     * @param searchTerm The term to search for.
     * @return True if the name or descriptions contain the search term, false otherwise.
     */
    @Override
    public boolean contains(String searchTerm) {

        if (super.name.contains(searchTerm)) {
            return true;
        }

        if (startDate != null) {
            return description1.contains(searchTerm) || description2.contains(searchTerm);
        }

        return false;

    }

}
