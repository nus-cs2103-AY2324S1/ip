package oscar.command;

import oscar.essential.InfoList;
import oscar.essential.Storage;

/**
 * Command to list infos in the info list.
 */
public class ListCommand extends Command {
    /**
     * Lists stored infos in chronological order of addition.
     *
     * @param infos   ArrayList of infos.
     * @param storage File loading and saving handler.
     * @return
     */
    @Override
    public String execute(InfoList infos, Storage storage) {
        assert infos != null;
        return infos.list();
    }
}
