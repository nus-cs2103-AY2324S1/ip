import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.closeAndGoodbyeMessage();
        try {
            storage.saveData(tasks);
        } catch (IOException e) {
            ui.showErrorMessage("Something went wrong with saving the tasks", e);
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
