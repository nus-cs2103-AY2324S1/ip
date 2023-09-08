package chatbuddy.command;

import java.util.ArrayList;

import chatbuddy.TaskList;
import chatbuddy.storage.Storage;
import chatbuddy.ui.Ui;

/** ListCommand represents a command to show the list of tasks. */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<String> taskStrings = tasks.getTaskStringsToPrint();
        return ui.showTaskList(taskStrings, "Here are the tasks in your list:");
    }
}
