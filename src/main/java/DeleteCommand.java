public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        Task temp = taskList.getTask(index);
        taskList.deleteTask(index);
        ui.printDeleteMessage(temp, taskList);
        storage.save(taskList);
    }
}
