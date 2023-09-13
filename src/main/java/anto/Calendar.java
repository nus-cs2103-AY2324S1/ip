package anto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Class encapsulates a Calendar storing schedule of events.
 */
public class Calendar {
    private HashMap<LocalDate, ArrayList<Event>> timeToEventsMap;

    /**
     * Creates a calendar class and instantiates a hashmap.
     */
    public Calendar() {
        this.timeToEventsMap = new HashMap<>();
    }

    /**
     * Add event to Calendar.
     *
     * @param event Event to add to Calendar.
     */
    public void addToCalendar(Event event) {
        LocalDateTime from = event.getFromDateTime();

        LocalDate date = from.toLocalDate();

        assert this.timeToEventsMap != null;
        if (!this.timeToEventsMap.containsKey(date)) {
            this.timeToEventsMap.put(date, new ArrayList<>());
        }
        ArrayList<Event> eventsOnDate = this.timeToEventsMap.get(date);
        eventsOnDate.add(event);
        eventsOnDate.sort(Comparator.comparing(Event::getFromDateTime));
    }

    /**
     * Remove event from calendar.
     *
     * @param event Event to remove from Calendar.
     */
    public void removeFromCalendar(Event event) {
        LocalDateTime from = event.getFromDateTime();

        LocalDate date = from.toLocalDate();

        assert this.timeToEventsMap != null;
        ArrayList<Event> eventsOnDate = this.timeToEventsMap.get(date);
        eventsOnDate.remove(event);
    }

    /**
     * Get events on the corresponding date.
     *
     * @param date Date to check for events.
     * @return ArrayList of events on the date given.
     */
    public ArrayList<Event> getEventsOnDate(LocalDate date) {
        assert this.timeToEventsMap != null;
        if (!this.timeToEventsMap.containsKey(date)) {
            return new ArrayList<>();
        }
        return this.timeToEventsMap.get(date);
    }
}
