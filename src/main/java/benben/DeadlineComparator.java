package benben;

import java.util.Comparator;

public class DeadlineComparator implements Comparator<Deadline> {
    @Override
    public int compare(Deadline d1, Deadline d2) {
        return d1.getDdl().compareTo(d2.getDdl());
    }
}
