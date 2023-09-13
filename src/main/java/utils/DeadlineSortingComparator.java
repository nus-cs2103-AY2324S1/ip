package utils;

import java.util.Comparator;

import task.Deadline;

/**
 * Implements Comparator class to compare between the dates and times of Deadline objects.
 */
public class DeadlineSortingComparator implements Comparator<Deadline> {

    /**
     * To compare the LocalDateTime of two Deadline objects.
     *
     * @param deadline1 The first Deadline object to be compared.
     * @param deadline2 The second Deadline object to be compared.
     * @return 0 if LocalDateTimes are equal,
     *     less than 0 if LocalDateTime of deadline1 is before that of deadline2,
     *     and more than 0 if LocalDateTime of deadline1 is after that of deadline2.
     */
    @Override
    public int compare(Deadline deadline1, Deadline deadline2) {
        return deadline1.getByDateTime().compareTo(deadline2.getByDateTime());
    }
}
