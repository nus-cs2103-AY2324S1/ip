package banterbot.command;

import banterbot.helper.Storage;
import banterbot.helper.Ui;
import banterbot.task.TaskList;

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
        assert !list.equals(null) : "list has been initialised";
        String message = list.print();
        return message;
    }
}
