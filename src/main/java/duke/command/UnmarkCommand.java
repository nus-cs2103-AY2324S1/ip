package duke.command;

import duke.Ui;
import duke.task.TaskList;

/**
 * Command to mark task from list as not done
 *
 * @author Lian Zhi Xuan
 */
public class UnmarkCommand extends Command{

    private int index;
    public UnmarkCommand(int i) {
        index = i;
    }

    @Override
    public String execute(TaskList list) {
        list.unmark(index);
        return Ui.instance.unmarkPrompt(list.getList().get(index));
    }

    public int index() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UnmarkCommand) {
            UnmarkCommand temp = (UnmarkCommand) o;
            return this.index == temp.index();
        }
        return false;
    }
}
