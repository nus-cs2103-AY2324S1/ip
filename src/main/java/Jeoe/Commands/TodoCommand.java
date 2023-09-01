package Jeoe.Commands;

import Jeoe.Others.StorageManager;
import Jeoe.Others.Ui;
import Jeoe.Tasks.TaskManager;
import Jeoe.Tasks.Todo;

public class TodoCommand extends Command {
    String todoDescription;
    TodoCommand (String input) {
        super(false);
        this.todoDescription = input.replaceFirst("todo ", "");
    }
    @Override
    public void execute(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        // create the actual task
        Todo todo = new Todo(todoDescription);

        // add to the storage in Task & save into HDD
        taskManager.addTask(todo);
        storageManager.save(taskManager.getTasks());

        // add to the reply
        ui.displayReply(todo.replyString(taskManager.getTasksSize()));
    }
}
