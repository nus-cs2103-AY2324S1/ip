package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.tasks.TaskList;
import chatbot.ui.Ui;
public class ListCommand extends Command{
    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
            ui.showList(tasksList);
    }
}
