package noac.command;

import noac.Storage;
import noac.TaskList;
import noac.Ui;
import noac.task.Todo;

public class TodoCommand extends Command {

    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){

        Todo t = new Todo(this.description);

        tasks.addTask(t);

        ui.showAddTask(t, tasks.size());

        storage.save(tasks);
    }
}
