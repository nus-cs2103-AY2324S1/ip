package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    String searchItem;

    public FindCommand(String searchItem) {
        this.searchItem = searchItem;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String str = "Here are the tasks in your list:\n\t ";
        int count = 0;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.toString().contains(this.searchItem)) {
                str += count + 1 + "." + task + "\n\t ";
                count++;
            }
        }
        ui.sendMessage(str.substring(0, str.length() - 3));
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
