package Command;

import Helper.*;
import Task.*;

/**
 * Represents a Command that specifically lists out all Tasks in the Tasklist.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand(int index) {
        super(index);
    }

    /**
     * Prints out all Tasks in the TaskList.
     * @param list
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.print(list.print());
    }
}
