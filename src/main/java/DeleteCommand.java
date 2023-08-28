public class DeleteCommand extends Command {
    private int index;

    DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("OOPS! The index to delete is invalid!");
        }
        Task deletedTask = taskList.get(index);
        taskList.delete(index);
        ui.deleteFromListSuccess(deletedTask, taskList.size());
        storage.saveList(taskList.getAllTasks());
    }
}
