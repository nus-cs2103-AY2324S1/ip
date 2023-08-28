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
}
