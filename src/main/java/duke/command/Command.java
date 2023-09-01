package duke.command;

import java.util.Map;

import duke.core.DukeException;
import duke.core.Storage;
import duke.core.Ui;

import duke.task.TaskList;

public abstract class Command {
    private Map<String, String> parameterMap;

    public Command(Map<String, String> parameterMap) {
        this.parameterMap = parameterMap;
    }

    /**
     * Returns the parameter map of the command.
     * 
     * @return Parameter map of the command.
     */
    public Map<String, String> getParameterMap() {
        return this.parameterMap;
    }

    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
