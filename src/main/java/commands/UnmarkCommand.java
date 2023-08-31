package commands;

import functions.*;
import tasks.*;

import java.io.IOException;

public class UnmarkCommand extends Command {
    protected int num;
    public UnmarkCommand(int num) {
        this.num = num;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = tasks.unmarkTask(this.num);
        ui.showUnmarkMsg(t);
        storage.saveFiles(tasks.showList());
    }
}
