package duke.task;

import java.util.Arrays;
import java.util.Comparator;

/**
 * A Comparator class to compare tasks by their categories.
 */
public class TaskCategoryComparator implements Comparator<Task> {
    /**
     * Compares given tasks by their categories.
     *
     * @param t1 the first object to be compared.
     * @param t2 the second object to be compared.
     * @return Returns integer value -1, 0, 1 if t1 is less than, equal to or greater than
     *         t2 respectively.
     */
    @Override
    public int compare(Task t1, Task t2) {
        String taskType1 = t1.toString().substring(1, 2);
        String taskType2 = t2.toString().substring(1, 2);

        String[] taskTypeOrder = {"T", "D", "E"};

        int index1 = Arrays.asList(taskTypeOrder).indexOf(taskType1);
        int index2 = Arrays.asList(taskTypeOrder).indexOf(taskType2);

        return Integer.compare(index1, index2);
    }
}
