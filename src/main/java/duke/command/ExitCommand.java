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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.saveData(taskList);
            return ui.showExit();
        } catch (IOException e) {
            return ui.showSavingError();
        }
    }
}
