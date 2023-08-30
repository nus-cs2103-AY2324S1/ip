public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskNumber > taskList.getNumberOfTasks()) {
            throw new DukeException("OOPS!!! Task " + taskNumber + " does not exist.");
        }
        ui.showTaskMarkedAsUndone(taskList.getTask(taskNumber));
        storage.save(taskList.getList(), ui);
    }
}
