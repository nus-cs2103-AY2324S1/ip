import java.io.IOException;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.saveData(taskList);
            ui.showExit();
        } catch (IOException e) {
            ui.showSavingError();
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
