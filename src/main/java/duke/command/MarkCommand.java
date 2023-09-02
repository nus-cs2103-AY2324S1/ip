package duke.command;

import duke.Duke;
import duke.Ui;
import duke.task.TaskList;

/**
 * Command to mark task from list as done
 *
 * @author Lian Zhi Xuan
 */
public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int i) {
        index = i;
    }

    @Override
    public void execute(TaskList list) {
        list.mark(index);
        Ui.ui.markPrompt(list.list().get(index));
        Duke.run();
    }

    public int index() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MarkCommand) {
            MarkCommand temp = (MarkCommand) o;
            return this.index == temp.index();
        }
        return false;
    }
}
