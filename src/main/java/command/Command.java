package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import task.Task;
import exceptions.DukeException;

public abstract class Command {
    private Boolean isExit;
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public Boolean isExit() {
        return this.isExit;
    }

    public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException;
}
