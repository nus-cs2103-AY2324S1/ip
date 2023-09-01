package command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * command.Command to end the Chat Bot. Saves user input into file.
 */
public class ByeCommand extends Command {
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
