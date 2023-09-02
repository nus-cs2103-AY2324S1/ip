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
    public void execute(TaskList list) {
        Ui.ui.listPrompt(list);
        Duke.run();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ListCommand;
    }
}
