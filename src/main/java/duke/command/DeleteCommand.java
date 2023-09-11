package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String execute(TaskList tasks) {
        Task task = tasks.get(taskNum - 1);
        tasks.deleteTask(this.taskNum - 1);
        String deleteMessage = Ui.deleteTask(task);

        return deleteMessage + "\n" + Ui.countTasks(tasks);
    }
}
