package benben;

import java.util.Comparator;

public class DescriptionComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        return t1.getDescription().compareTo(t2.getDescription());
    }
}
