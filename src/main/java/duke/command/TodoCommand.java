package duke.command;

import duke.RichieException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;


public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws RichieException {
        Todo todoTask = new Todo(this.description);
        taskList.addTask(todoTask);
        storage.saveCurrentTasks(taskList.getTaskArray());
        ui.showMessage("Got it. I've added this task:\n" + todoTask.toString()
                + "\nNow you have " + taskList.getSize() + " tasks in the list.");
    }
}
