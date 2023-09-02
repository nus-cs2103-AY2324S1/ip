package duke.command;

import duke.exception.InvalidParametersException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/** Command interface **/
public interface Command {
    void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidParametersException;
    boolean isExit();
}
