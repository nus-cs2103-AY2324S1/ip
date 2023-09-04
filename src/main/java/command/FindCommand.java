package command;

import helper.Storage;
import helper.Ui;
import task.TaskList;

/**
 * Represents a Command that specifically finds a Task by a substring.
 */
public class FindCommand extends Command {
    /** Command the user starts with to activate the FindCommand. */
    public static final String COMMAND_WORD = "find";
    /** Substring to find in description of Task. */
    private String substring;

    /**
     * Constructs a FindCommand that finds a List of Tasks with description
     * containing the substring.
     * @param index
     * @param substring
     */
    public FindCommand(int index, String substring) {
        super(index);
        this.substring = substring;
    }

    /**
     * Prints out all Tasks that contain the substring specified by the User in its description.
     * @param list
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        String message = list.find(substring);
        ui.print(message);
    }
}
