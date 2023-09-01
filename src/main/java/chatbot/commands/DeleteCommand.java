package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.tasks.TaskList;
import chatbot.ui.Ui;
public class DeleteCommand extends Command{
    private final int num;

    public DeleteCommand(int num){
        this.num = num;
    }
    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        ui.showRemovedTask(tasksList, num);
        tasksList.removeTask(num);
    }
}
