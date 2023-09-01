package chatbuddy.command;

import chatbuddy.storage.Storage;
import chatbuddy.TaskList;
import chatbuddy.ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<String> taskStrings = tasks.getTaskStringsToPrint();
        ui.showTaskList(taskStrings, "Here are the tasks in your list:");
    }
}
