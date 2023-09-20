package benben;

import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event t1, Event t2) {
        return t1.getStartTime().compareTo(t2.getStartTime());
    }
}