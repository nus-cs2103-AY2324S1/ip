package duke.command;

import duke.exception.DukeInvalidMarkException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private String[] splitTask;

    public MarkCommand(String[] splitTask) {

        this.splitTask = splitTask;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        int index;

        try {
            index = Integer.parseInt(splitTask[1]);
        } catch (NumberFormatException e) {
            throw new DukeInvalidMarkException(splitTask[0]);
        }

        if (index > 0 && index < taskList.size() && taskList.get(index - 1) != null) {
            taskList.get(index - 1).mark();
            ui.printMark(taskList.get(index - 1));
        } else {
            throw new DukeInvalidMarkException(Integer.toString(index));
        }
        storage.writeFile(taskList);
    }
}
