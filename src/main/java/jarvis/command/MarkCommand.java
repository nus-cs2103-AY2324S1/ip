package jarvis.command;

import jarvis.storage.Storage;
import jarvis.task.Task;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;
import jarvis.exception.JarvisException;
import jarvis.exception.JarvisInvalidIndexException;

public class MarkCommand extends Command {
    private int taskIndexToMark;

    public MarkCommand(int taskIndexToMark) {
        this.taskIndexToMark = taskIndexToMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JarvisException {
        if (taskIndexToMark <= 0 || taskIndexToMark > tasks.size()) {
            throw new JarvisInvalidIndexException(taskIndexToMark);
        }

        Task taskToMark = tasks.get(taskIndexToMark - 1);
        taskToMark.mark();

        ui.displayMarkedTask(taskToMark);

        storage.saveTasks(tasks);
    }
}