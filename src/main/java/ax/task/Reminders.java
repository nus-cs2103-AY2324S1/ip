package ax.task;

import java.time.LocalDate;

/**
 * Reminders interface that will check if an item is due
 */
public interface Reminders {

    /**
     * check if the reminderDate is today or previous days, which means reminder is due
     * @param reminderDate the LocalDate of the due date of the reminder
     * @return boolean, true if reminder is due and false if it is not
     */
    default boolean isDue(LocalDate reminderDate) {
        if (reminderDate.isEqual(LocalDate.now()) || reminderDate.isBefore(LocalDate.now())) {
            return true;
        }
        return false;
    }
}
