package duke.command;

import duke.OutOfRangeException;
import duke.SaveToFileException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.AlreadyMarkedException;
import duke.task.AlreadyUnmarkedException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SaveToFileException,
            OutOfRangeException, AlreadyMarkedException, AlreadyUnmarkedException;
    public boolean isExit() {
        return false;
    };
}
