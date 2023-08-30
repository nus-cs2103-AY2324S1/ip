package command;

import exception.DukeException;
import helper.Storage;
import helper.Ui;
import task.TaskList;

public abstract class Command {
    private int index = -1;

    public Command(int index) {
        this.index = index;
    }

    public boolean isExit() {
        return false;
    }

    public int getIndex() {
        return this.index;
    }

    public abstract void execute(TaskList list, Ui ui, Storage storage) throws DukeException;
}
