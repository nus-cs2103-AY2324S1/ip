package duke.command;

import duke.Ui;
import duke.task.TaskList;

/**
 * Command to show list
 *
 * @author Lian Zhi Xuan
 */
public class ListCommand extends Command{

    /**
     * Returns the entire list of tasks.
     *
     * @param list TaskList to be modified.
     * @return string representation of the list.
     */
    @Override
    public String execute(TaskList list) {
        return Ui.instance.listPrompt(list);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ListCommand;
    }
}
