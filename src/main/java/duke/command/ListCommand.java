package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand implements Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String output = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                output += "\n" + (i + 1) + "." + tasks.get(i).toString();
            }
        }
        ui.sendMessage(output);
    }

    @Override
    public void loadTask(TaskList tasks) {
        //Do nothing
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
