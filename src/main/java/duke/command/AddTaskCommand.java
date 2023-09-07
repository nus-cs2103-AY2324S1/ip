package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents the command of adding a task to the list.
 */
public class AddTaskCommand extends Command {
    private final Task newTask;

    public AddTaskCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(newTask);
        storage.saveTasks(tasks);
        return ui.showTaskAdded(newTask, tasks.size());
    }
}
