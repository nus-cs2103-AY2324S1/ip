package duke.command;
import duke.exception.DukeException;
import duke.main.TaskList;
import duke.main.Storage;
import duke.main.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public boolean isExit() {
        return false;
    }
}
