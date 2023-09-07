package command;

import java.util.Objects;

import exception.DukeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class EditCommand extends Command {
    private final int taskIndex;
    private final String description;

    public EditCommand(String description, int taskIndex) {
        super(null);
        this.taskIndex = taskIndex;
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (taskIndex <= 0 || taskIndex >= tasks.size()) {
                throw new DukeException(ui.invalidIndexError(taskIndex));
            }
            if (Objects.equals(description, "mark")) {
                Task currTask = tasks.getList().get(taskIndex - 1);
                currTask.setCompleted();
                ui.showMarkedTask(taskIndex, currTask);
            } else if (Objects.equals(description, "unmark")) {
                Task currTask = tasks.getList().get(taskIndex - 1);
                currTask.setNotCompleted();
                ui.showUnmarkedTask(taskIndex, currTask);
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }




}
