package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.net.SocketTimeoutException;

public interface Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public void loadTask(TaskList tasks);

    public boolean isExit();
}
