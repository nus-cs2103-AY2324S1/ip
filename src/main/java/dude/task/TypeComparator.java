package dude.task;

import java.util.Comparator;

/**
 * Comparator for Task that orders by type.
 */
public class TypeComparator implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        // gets type by checking the <X> prefix in string
        String type1 = o1.toString().substring(0, 3);
        String type2 = o2.toString().substring(0, 3);
        return type1.compareTo(type2);
    }
}
