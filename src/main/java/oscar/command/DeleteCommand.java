package oscar.command;

import oscar.essential.InfoList;
import oscar.essential.Storage;
import oscar.exception.OscarException;

/**
 * Command to delete an info from the info list.
 */
public class DeleteCommand extends Command {
    private final String details;

    /**
     * Instantiates a delete command.
     *
     * @param d Info number to be deleted.
     */
    public DeleteCommand(String d) {
        this.details = d;
    }

    /**
     * Deletes an info using the info number.
     *
     * @param infos   ArrayList of infos.
     * @param storage File loading and saving handler.
     * @return String output of deletion command.
     * @throws OscarException Failure to validate info number.
     */
    @Override
    public String execute(InfoList infos, Storage storage) throws OscarException {
        assert infos != null;
        assert storage != null;
        int index = validateInt(infos, details);
        String currentInfo = infos.delete(index);
        storage.save(infos);
        return "Oscar has removed this info:\n" + currentInfo + "\n" + infos.listCount();
    }
}
