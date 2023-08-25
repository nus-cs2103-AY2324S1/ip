package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.UI;

public interface Command {

    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException;

    public boolean isExit();

}
