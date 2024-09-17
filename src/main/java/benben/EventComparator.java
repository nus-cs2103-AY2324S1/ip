package benben;

import java.util.Comparator;

/**
 * A comparator that compares the event by their start time.
 */
public class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event t1, Event t2) {
        return t1.getStartTime().compareTo(t2.getStartTime());
    }
}