package command;

import helper.Storage;
import helper.Ui;
import task.TaskList;

/**
 * Represents a Command that specifically lists out all Tasks in the Tasklist.
 */
public class ListCommand extends Command {
    /** Command the user starts with to activate the ListCommand. */
    public static final String COMMAND_WORD = "list";

    /**
     * Constructs a ListCommand with an Index.
     * @param index
     */
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
    public String execute(TaskList list, Ui ui, Storage storage) {
        String message = list.print();
        //        ui.print(message);
        return message;
    }
}
