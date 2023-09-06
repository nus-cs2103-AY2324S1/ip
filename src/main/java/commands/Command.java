package commands;

import functional.DukeException;
import functional.TaskList;
import functional.Ui;
import tasks.Task;


public class Command {
    boolean hasExit;

    public Command() {
        this.hasExit = false;
    }

    public void execute(TaskList<Task> tasks, Ui ui, boolean marked, boolean load) throws DukeException {
    }

    public boolean hasExit() {
        return this.hasExit;
    }
}
