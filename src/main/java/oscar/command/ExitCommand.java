package oscar.command;

import oscar.essential.InfoList;
import oscar.essential.Storage;

/**
 * Command to terminate Oscar.
 */
public class ExitCommand extends Command {
    /**
     * Displays message when terminating Oscar.
     *
     * @param infos   ArrayList of infos.
     * @param storage File loading and saving handler.
     * @return
     */
    @Override
    public String execute(InfoList infos, Storage storage) {
        return "Goodbye for now. Oscar hopes to see you again soon!\n";
    }
}
