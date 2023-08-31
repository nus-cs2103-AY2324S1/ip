package duke.command;

import duke.exception.DukeInvalidDeleteException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command{
    private String[] splitTask;

    public DeleteCommand(String[] splitTask) {
        this.splitTask = splitTask;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        int index;

        try {
            index = Integer.parseInt(splitTask[1]);
        } catch (NumberFormatException e) {
            throw new DukeInvalidDeleteException(splitTask[0]);
        }

        if (index > 0 && taskList.get(index - 1) != null) {
            ui.printDeleteTask(taskList.get(index-1), taskList.size() - 1);
            taskList.remove(index - 1);

        }
        storage.writeFile(taskList);
    }
}
