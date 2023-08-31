package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class TaskCommand extends Command {
    private String[] splitTask;

    public TaskCommand(String[] splitTask) {
        this.splitTask = splitTask;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        Task newTask = Task.createTaskType(splitTask);
        taskList.add(newTask);
        int i = taskList.indexOf(newTask);

        ui.printAddTask(newTask, i + 1);
        storage.writeFile(taskList);
    }
}
