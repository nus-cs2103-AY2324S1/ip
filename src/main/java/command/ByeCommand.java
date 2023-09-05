package command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to end the Chat Bot. Saves user input into file.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) {
        try {
            store.writeFile(tasks);
        } catch (IOException e) {
            ui.showWritingError();
        }

        return ui.endBot();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
