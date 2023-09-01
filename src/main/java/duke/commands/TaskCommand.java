package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

public abstract class TaskCommand extends Command {

    protected String description;
    protected boolean isDone;

    public TaskCommand(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    public void load(TaskList tasklist) {
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
    }
}
