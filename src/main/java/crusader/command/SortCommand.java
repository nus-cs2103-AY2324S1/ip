package crusader.command;

import crusader.Storage;
import crusader.TaskList;
import crusader.exception.CrusaderException;

/**
 * Command used to sort items in the list.
 */
public class SortCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage) throws CrusaderException {
        taskList.sort();
        return String.format(
                "Sorting the list now.\nHere is your list now:\n%s",
                taskList.toString()
        );
    }
}
