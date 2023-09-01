package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.tasks.Todo;
import chatbot.ui.Ui;
public class TodoCommand extends Command{
    String description;

    public TodoCommand(String description){

        this.description = description;
    }
    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        Task todo = new Todo(description);
        tasksList.addTask(todo);
        ui.showAddedTask(tasksList);
    }
}
