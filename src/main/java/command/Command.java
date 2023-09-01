package command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;

import task.TaskList;

public class Command {
    protected boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }

    protected void setExit(boolean exit) {
        isExit = exit;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println("This method is to be implemented by child classes");
    }
}
