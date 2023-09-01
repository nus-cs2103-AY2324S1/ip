package duke.command;

import java.util.Map;

import duke.core.DukeException;
import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;

public abstract class Command {
    Map<String, String> parameterMap;

    public Command(Map<String, String> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
