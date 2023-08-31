package max.commands;
import max.commands.*;
import max.exception.MaxException;
import max.parser.Parser;
import max.storage.Storage;
import max.tasks.TaskList;
import max.tasks.Task;
import max.ui.Ui;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private int taskNumber;
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        Task unmark = tasks.getList().get(taskNumber - 1);
        tasks.unmark(taskNumber);
        storage.writeToFile(tasks);
        ui.showUnmark(unmark);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
