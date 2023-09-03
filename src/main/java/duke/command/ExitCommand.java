package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command to exit the chatbot.
 */
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
