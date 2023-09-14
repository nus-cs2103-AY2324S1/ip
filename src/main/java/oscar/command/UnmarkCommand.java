package oscar.command;

import oscar.essential.InfoList;
import oscar.essential.Storage;
import oscar.exception.OscarException;

/**
 * Command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final String details;

    /**
     * Instantiates an unmark command.
     *
     * @param d Task number to be marked.
     */
    public UnmarkCommand(String d) {
        this.details = d;
    }

    /**
     * Marks a task as not done using the task number.
     *
     * @param infos   ArrayList of infos.
     * @param storage File loading and saving handler.
     * @return String output of unmark command.
     * @throws OscarException Failure to validate task number.
     */
    @Override
    public String execute(InfoList infos, Storage storage) throws OscarException {
        assert infos != null;
        assert storage != null;
        int index = validateInt(infos, details);
        String currentTask = infos.unmark(index);
        storage.save(infos);
        return "Oscar has marked this task as not done yet:\n" + currentTask + "\n";
    }
}
