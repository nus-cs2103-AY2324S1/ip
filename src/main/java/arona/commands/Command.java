package arona.commands;

import arona.task.TaskList;
import arona.ui.Ui;

public abstract class Command {
    protected TaskList taskList;
    protected Ui ui;

    public Command(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public abstract void execute();
}
