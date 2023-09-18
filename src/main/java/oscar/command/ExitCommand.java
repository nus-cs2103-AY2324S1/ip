package oscar.command;

import oscar.essential.ItemList;
import oscar.essential.Storage;

/**
 * Command to terminate Oscar.
 */
public class ExitCommand extends Command {
    public static final String EXIT_MESSAGE = "Goodbye for now. Oscar hopes to see you again soon!\n";
    /**
     * Displays message when terminating Oscar.
     *
     * @param infos   ArrayList of infos.
     * @param storage File loading and saving handler.
     * @return
     */
    @Override
    public String execute(ItemList infos, Storage storage) {
        return EXIT_MESSAGE;
    }
}
