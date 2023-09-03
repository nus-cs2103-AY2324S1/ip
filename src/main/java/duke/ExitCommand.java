package duke;

import java.io.IOException;

/**
 * Exits duke and saves task list to storage
 */
public class ExitCommand extends Command{

    public ExitCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException{
        storage.save(tasks);
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
