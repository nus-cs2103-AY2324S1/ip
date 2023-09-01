package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.Objects;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task temp = taskList.getTask(index);
        temp.mark();
        ui.printMarkMessage(temp);
        storage.save(taskList);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof MarkCommand) {
            MarkCommand temp = (MarkCommand) obj;
            if (this.index == temp.index) {
                return true;
            }
        }
        return false;
    }

}
