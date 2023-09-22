package dude.task;

import java.util.Comparator;

/**
 * Comparator for Task that orders lexicographically by description.
 */
public class DescriptionComparator implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        return o1.description.compareTo(o2.description);
    }
}
