package utils;

import java.util.Comparator;

import task.Event;

/**
 * Implements Comparator class to compare between the dates and times of Event objects.
 */
public class EventSortingComparator implements Comparator<Event> {

    /**
     * To compare the LocalDateTime of two Event objects.
     *
     * @param event1 The first Event object to be compared.
     * @param event2 The second Event object to be compared.
     * @return 0 if LocalDateTimes are equal,
     *     less than 0 if LocalDateTime of event1 is before that of event2,
     *     and more than 0 if LocalDateTime of event1 is after that of event2.
     */
    @Override
    public int compare(Event event1, Event event2) {
        return event1.getStartDateTime().compareTo(event2.getStartDateTime());
    }
}
