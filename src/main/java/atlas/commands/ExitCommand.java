package atlas.commands;

import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.components.Ui;

/**
 * Command to exit the chatbot
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printToScreen("Goodbye!");
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "Goodbye!";
    }
}
