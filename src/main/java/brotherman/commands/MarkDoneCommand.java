package brotherman.commands;

import brotherman.storage.Storage;
import brotherman.tasks.TaskList;
import brotherman.ui.Ui;

public class MarkDoneCommand extends Command {
    private int taskNum;
    public MarkDoneCommand(int taskNum) {
        super(false);
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markDone(taskNum);
    }
}
