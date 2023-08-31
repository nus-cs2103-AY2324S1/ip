package max.commands;
import max.commands.*;
import max.exception.MaxException;
import max.parser.Parser;
import max.storage.Storage;
import max.tasks.TaskList;
import max.tasks.*;
import max.ui.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public ExitCommand() {

    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        storage.writeToFile(tasks);
        ui.exit();
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
