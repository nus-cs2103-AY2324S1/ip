package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Command to terminate program
 *
 * @author Lian Zhi Xuan
 */
public class TerminateCommand extends Command {
    @Override
    public void execute(TaskList list) {
        Storage storage = new Storage();
        storage.save(list);
        Ui.ui.endPrompt();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof TerminateCommand;
    }

}
