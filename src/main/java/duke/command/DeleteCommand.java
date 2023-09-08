package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.*;

/**
 * Command to delete task from list
 *
 * @author Lian Zhi Xuan
 */
public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int i) {
        index = i;
    }

    @Override
    public String execute(TaskList list) {
        Task temp = list.list().get(index);
        list.delete(index);
        return Ui.ui.deletePrompt(temp);
    }

    public int index() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DeleteCommand) {
            DeleteCommand temp = (DeleteCommand) o;
            return this.index == ((DeleteCommand) o).index();
        }
        return false;
    }
}
