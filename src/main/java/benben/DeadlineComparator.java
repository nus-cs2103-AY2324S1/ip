package benben;

import java.util.Comparator;

/**
 * A comparator that compares the deadline objects by their deadline
 */
public class DeadlineComparator implements Comparator<Deadline> {
    @Override
    public int compare(Deadline d1, Deadline d2) {
        return d1.getDdl().compareTo(d2.getDdl());
    }
}
