import java.io.IOException;

public class ByeCommand extends Command {
    public ByeCommand(){};

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) {
        try {
            store.writeFile(tasks);
        } catch (IOException e) {
            ui.showWritingError();
        }
    }

    @Override
    public boolean isExit() {
        return true;
    };
}
