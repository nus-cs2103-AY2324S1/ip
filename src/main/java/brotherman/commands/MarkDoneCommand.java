package brotherman.commands;

import brotherman.storage.*;
import brotherman.tasks.*;
import brotherman.ui.*;

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
