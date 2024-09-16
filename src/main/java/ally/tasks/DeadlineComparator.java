package ally.tasks;

import ally.exceptions.AllyException;
import ally.Ally;
import ally.tasks.AllyList;
import java.util.Comparator;
import  java.util.Collections;
import java.util.List;
import java.lang.Comparable;

public class DeadlineComparator implements Comparator<Deadline> {
    @Override
    public int compare(Deadline deadline1, Deadline deadline2) {
        return deadline1.getByDateTime().compareTo(deadline2.getByDateTime());
    }
}
