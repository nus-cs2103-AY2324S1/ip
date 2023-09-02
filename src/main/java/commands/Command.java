package commands;
import exception.URChatBotException;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

public class Command {
    public String taskDescription;

    public Command(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isExit(){
        return false;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws URChatBotException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }
}
