package duke.command;

import duke.task.TaskList;
import duke.*;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int i) {
        index = i;
    }

    @Override
    public void execute(TaskList list) {
        Ui.ui.deletePrompt(list.list().get(index));
        list.delete(index);
        Duke.run();
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
