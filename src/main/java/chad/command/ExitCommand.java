package chad.command;

import chad.exception.SaveException;
import chad.util.Storage;
import chad.util.TaskList;
import chad.util.Ui;

/**
 * Represents an Exit command to be executed.
 */
public class ExitCommand extends Command {

    /**
     * Executes the list of commands to exit the chatbot.
     *
     * @param taskList The given TaskList associated with the chatbot.
     * @param ui The given Ui to show the status of the exit command.
     * @param storage The given Storage that saves the TaskList locally.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.save(taskList);
        } catch (SaveException e) {
            ui.addErrorMessage(e);
        }
        ui.addExitMessage();
    }
}
