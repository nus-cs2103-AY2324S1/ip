package max.commands;
import max.commands.*;
import max.exception.MaxException;
import max.parser.Parser;
import max.storage.Storage;
import max.tasks.TaskList;
import max.tasks.*;
import max.ui.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int taskNumber;
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        Task deleted = tasks.getList().get(taskNumber - 1);
        tasks.delete(taskNumber);
        storage.writeToFile(tasks);
        ui.showDelete(deleted, tasks.getList().size());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
