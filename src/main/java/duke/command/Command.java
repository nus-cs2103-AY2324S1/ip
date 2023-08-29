package duke.command;
import duke.task.TaskList;
import duke.task.DukeException;
import duke.helper.Ui;
import duke.helper.Storage;

public abstract class Command {

    boolean isExit;
    public abstract void execute(TaskList tasks, Ui ui, Storage store) throws DukeException;

    public Command() {
        this.isExit = false;
    }

    public boolean isExit() {
        return false;
    }

}
