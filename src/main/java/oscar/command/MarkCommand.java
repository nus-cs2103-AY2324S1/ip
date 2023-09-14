package oscar.command;

import oscar.essential.InfoList;
import oscar.essential.Storage;
import oscar.exception.OscarException;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final String details;

    /**
     * Instantiates a mark command.
     *
     * @param d Task number to be marked.
     */
    public MarkCommand(String d) {
        this.details = d;
    }

    /**
     * Marks a task as done using the task number.
     *
     * @param infos   ArrayList of infos.
     * @param storage File loading and saving handler.
     * @return String output of mark command.
     * @throws OscarException Failure to validate task number.
     */
    @Override
    public String execute(InfoList infos, Storage storage) throws OscarException {
        assert infos != null;
        assert storage != null;
        int index = validateInt(infos, details);
        try {
            String currentTask = infos.mark(index);
            storage.save(infos);
            return "Nice! Oscar has marked this task as done:\n" + currentTask + "\n";
        } catch (OscarException e) {
            return e.getMessage();
        }
    }
}
