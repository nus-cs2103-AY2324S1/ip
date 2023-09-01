public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task removedTask = taskList.deleteTask(taskIndex);
            ui.showDeletedTask(removedTask, taskList.getTaskCount());
            storage.saveTask(taskList.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task index is out of range!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
