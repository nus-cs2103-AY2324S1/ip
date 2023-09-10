package tasket.command;

import tasket.storage.Storage;
import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super("");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TasketException {
        String[] taskStrings = new String[taskList.size()];

        for (int i = 0; i < taskStrings.length; i++) {
            taskStrings[i] = taskList.getTaskString(i);
        }

        ui.showTaskList(taskStrings);
    }
}
