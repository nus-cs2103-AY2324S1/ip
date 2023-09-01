package Commands;

import Others.StorageManager;
import Others.Ui;
import Tasks.Task;
import Tasks.TaskManager;
import Tasks.Todo;

import java.util.ArrayList;

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
