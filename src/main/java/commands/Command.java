package commands;

import functional.DukeException;
import functional.TaskList;
import functional.Ui;
import tasks.Task;


public class Command {
    boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public void execute(TaskList<Task> tasks, Ui ui, boolean marked, boolean load) throws DukeException {
    }

    public boolean isExit() {
        return this.isExit;
    }
}
