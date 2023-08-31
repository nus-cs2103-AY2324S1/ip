package helpbuddy.command;

import java.io.IOException;

import helpbuddy.storage.Storage;
import helpbuddy.task.TaskList;
import helpbuddy.ui.Ui;

/**
 * The ExitCommand class calls Ui to print farewell message and closes HelpBuddy.
 */
public class ExitCommand extends Command {

    /**
     * Saves data from taskList into storage and calls Ui to print farewell message.
     * Finally, closes HelpBuddy chatbot.
     * @param taskList the tasklist with Task to be saved.
     * @param ui the ui that prints message.
     * @param storage the storage with saved data in TaskList.
     * @throws IOException if unable to write data to file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        storage.saveData(taskList);
        super.toggleExit();
        ui.printByeMessage();
        ui.closeScanner();
    }
}
