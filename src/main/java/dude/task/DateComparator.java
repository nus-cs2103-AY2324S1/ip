package dude.task;

import java.time.LocalDateTime;
import java.util.Comparator;

import dude.command.SortByOrder;

/**
 * Comparator for Task that orders by deadline/event start date.
 * <p>
 * Puts tasks with no date at the end. (e.g. Todo tasks)
 */
public class DateComparator implements Comparator<Task> {
    private final SortByOrder sortByOrder;

    /**
     * Constructor for DateComparator.
     *
     * @param sortByOrder Order to sort by.
     */
    public DateComparator(SortByOrder sortByOrder) {
        super();
        this.sortByOrder = sortByOrder;
    }

    /**
     * Gets datetime to compare from task.
     *
     * @param task Task to convert to datetime.
     * @return Datetime of task to compare.
     */
    private LocalDateTime getDateFrom(Task task) {
        if (task instanceof DeadlineTask) {
            return ((DeadlineTask) task).deadline;
        } else if (task instanceof EventTask) {
            return ((EventTask) task).startTime;
        } else {
            // puts tasks that do not have a date to compare at the end of the list
            if (sortByOrder == SortByOrder.ascending) {
                return LocalDateTime.MAX;
            } else if (sortByOrder == SortByOrder.descending) {
                return LocalDateTime.MIN;
            } else {
                return LocalDateTime.MIN;
            }
        }
    }

    @Override
    public int compare(Task o1, Task o2) {
        if (sortByOrder == SortByOrder.ascending) {
            return getDateFrom(o1).compareTo(getDateFrom(o2));
        } else if (sortByOrder == SortByOrder.descending) {
            return getDateFrom(o2).compareTo(getDateFrom(o1));
        } else {
            return 0;
        }
    }
}
