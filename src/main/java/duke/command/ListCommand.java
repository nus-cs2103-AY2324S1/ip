package duke.command;

import duke.Duke;
import duke.Ui;
import duke.task.TaskList;

/**
 * Command to show list
 *
 * @author Lian Zhi Xuan
 */
public class ListCommand extends Command{

    @Override
    public String execute(TaskList list) {
        return Ui.ui.listPrompt(list);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ListCommand;
    }
}
