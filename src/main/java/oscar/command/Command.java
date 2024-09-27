package oscar.command;

import java.time.format.DateTimeFormatter;

import oscar.essential.ItemList;
import oscar.essential.Storage;
import oscar.exception.OscarException;

/**
 * Abstract class for various Oscar commands.
 */
public abstract class Command {
    static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Validates the provided info number.
     *
     * @param details Info number as a string.
     * @param infos   ArrayList of infos.
     * @return Integer info number.
     * @throws OscarException Failure to validate info number.
     */
    public int validateInt(ItemList infos, String details) throws OscarException {
        if (details.isEmpty()) {
            throw new OscarException("Sorry! The info number cannot be empty.\n");
        }
        int index;
        try {
            index = Integer.parseInt(details) - 1;
            if (index < 0) {
                throw new OscarException("Sorry! Info numbers must be natural numbers.\n");
            } else if (index >= infos.getSize()) {
                throw new OscarException("Sorry! Info number is too large.\n");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new OscarException("Sorry! Please enter the number of the task to be marked as done.\n");
        }
    }

    /**
     * Carries out the intended function of a command.
     *
     * @param infos   ArrayList of infos.
     * @param storage File loading and saving handler.
     * @return String output of command.
     * @throws OscarException Failure to execute command.
     */
    public abstract String execute(ItemList infos, Storage storage) throws OscarException;
}
