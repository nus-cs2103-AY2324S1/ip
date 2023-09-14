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
    public String execute(TaskList list) {
        Storage storage = new Storage();
        storage.save(list);
        return Ui.instance.endPrompt();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof TerminateCommand;
    }

}
