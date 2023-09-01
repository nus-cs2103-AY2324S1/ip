package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

import java.util.ArrayList;

public abstract class Command {
    protected ArrayList<String> params;

    public Command(ArrayList<String> params) {
        this.params = params;
    }

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
