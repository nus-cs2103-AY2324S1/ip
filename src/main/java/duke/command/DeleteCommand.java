package duke.command;

import duke.*;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new DukeException("Invalid task number!");
        }

        Task deletedTask = tasks.get(taskNumber - 1);
        tasks.delete(taskNumber - 1);

        try {
            storage.saveTasks(tasks); // Save the updated tasks to file
        } catch (IOException e) {
            return ui.showSavingError(e.getMessage());
        }

        return ui.showDeletedTask(deletedTask, tasks.size());
    }
}
