package jarvis.command;

import jarvis.storage.Storage;
import jarvis.task.Task;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;
import jarvis.exception.JarvisException;
import jarvis.exception.JarvisInvalidIndexException;

public class UnmarkCommand extends Command {
    private int taskIndexToUnmark;

    public UnmarkCommand(int taskIndexToUnmark) {
        this.taskIndexToUnmark = taskIndexToUnmark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JarvisException {
        if (taskIndexToUnmark <= 0 || taskIndexToUnmark > tasks.size()) {
            throw new JarvisInvalidIndexException(taskIndexToUnmark);
        }

        Task taskToUnmark = tasks.get(taskIndexToUnmark - 1);
        taskToUnmark.unmark();

        ui.displayUnmarkedTask(taskToUnmark);

        storage.saveTasks(tasks);
    }
}