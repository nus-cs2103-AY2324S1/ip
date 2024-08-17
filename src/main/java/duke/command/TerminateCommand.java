package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.application.Platform;

/**
 * Command to terminate program
 *
 * @author Lian Zhi Xuan
 */
public class TerminateCommand extends Command {

    /**
     * Terminates duke.
     *
     * @param list TaskList to be modified.
     * @return ending prompt.
     */
    @Override
    public String execute(TaskList list) {
        Storage storage = new Storage();
        storage.save(list);
        Platform.exit();
        return Ui.instance.endPrompt();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof TerminateCommand;
    }

}
