package duke.command;

import duke.Storage;
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

    /**
     * Unmarks selected task as done.
     *
     * @param list TaskList to be modified.
     * @return prompt for marking the task.
     */
    @Override
    public String execute(TaskList list) {
        list.unmark(index);
        Storage.instance.save(list);
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
