package max.commands;
import max.commands.*;
import max.exception.MaxException;
import max.parser.Parser;
import max.storage.Storage;
import max.tasks.TaskList;
import max.tasks.*;
import max.ui.Ui;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private int taskNumber;
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        Task mark = tasks.getList().get(taskNumber - 1);
        tasks.mark(taskNumber);
        storage.writeToFile(tasks);
        ui.showMark(mark);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
