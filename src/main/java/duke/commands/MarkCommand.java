package duke.commands;

import duke.Task;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private final int taskNum;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String execute(TaskList tasks) {
        Task task = tasks.get(taskNum - 1);
        tasks.markAsDone(this.taskNum - 1);
        return Ui.markAsDone(task);
    }
}
