package duke.task;

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
        String taskString = t1.toString();
        String otherTaskString = t2.toString();

        boolean isTaskToDo = taskString.startsWith("[T]");
        boolean isTaskDeadline = taskString.startsWith("[D]");
        boolean isTaskEvent = taskString.startsWith("[E]");

        boolean isOtherTaskToDo = otherTaskString.startsWith("[T]");
        boolean isOtherTaskDeadline = otherTaskString.startsWith("[D]");
        boolean isOtherTaskEvent = otherTaskString.startsWith("[E]");

        if (isTaskToDo) {
            if (isOtherTaskToDo) {
                return 0;
            } else {
                return -1;
            }
        } else if (isTaskDeadline) {
            if (isOtherTaskToDo) {
                return 1;
            } else if (isOtherTaskDeadline) {
                return 0;
            } else {
                return -1;
            }
        } else if (isTaskEvent) {
            if (isOtherTaskToDo || isOtherTaskDeadline) {
                return 1;
            } else if (isOtherTaskEvent) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }
}
