package dook.command;

import dook.*;
import dook.services.Storage;
import dook.services.TaskList;
import dook.services.UiDisplay;
import dook.task.Task;

public class AddTaskCommand extends Command {
    private final Task task;
    public AddTaskCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException {

        uiDisplay.printMessage(taskList.addTask(task));
    }
}
