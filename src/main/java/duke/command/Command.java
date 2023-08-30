package duke.command;

import java.util.Map;

import duke.core.DukeException;
import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;

public abstract class Command {
    Map<String, String> parameterMap;

    protected Command() {}

    public void addParameterMap(Map<String, String> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
