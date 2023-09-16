package barbie.commands;

import java.util.ArrayList;

import barbie.Storage;
import barbie.Ui;
import barbie.types.Task;
import barbie.types.Todo;

public class TodoCommand extends Command {

    String desc;
    public TodoCommand(String desc) {
        this.desc = desc;
        this.isExit = false;

    }

    @Override
    public String run(ArrayList<Task> taskList) {
        Todo todo = new Todo(this.desc);
        taskList.add(todo);
        Storage.addToList(this.desc);
        return Ui.taskAdded(todo);

    }
}
