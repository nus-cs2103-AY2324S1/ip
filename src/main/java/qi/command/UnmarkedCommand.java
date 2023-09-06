package qi.command;

import qi.qiexception.QiException;
import qi.storage.Storage;
import qi.tasklist.TaskList;
import qi.ui.Ui;

import java.io.IOException;

public class UnmarkedCommand extends Command {

    private int taskId;

    public UnmarkedCommand(int taskId) {
        super(false);
        this.taskId = taskId;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws QiException {
        ui.showTaskUnmarked(this.taskId, tasks);
        try {
            storage.update(tasks);
        } catch (IOException e) {
            throw new QiException("Cannot write to file!");
        }
    }
}
