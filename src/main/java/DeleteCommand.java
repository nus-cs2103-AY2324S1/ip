import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new DukeException("Invalid task number!");
        }

        Task deletedTask = tasks.get(taskNumber - 1);
        tasks.delete(taskNumber - 1);

        try {
            storage.saveTasks(tasks); // Save the updated tasks to file
        } catch (IOException e) {
            ui.showSavingError(e.getMessage());
        }

        ui.showDeletedTask(deletedTask, tasks.size());
    }
}
