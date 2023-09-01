public class DeleteCommand extends Command {

    private int num;
    public DeleteCommand(int num) {
        this.num = num;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.delete(this.num);
        ui.printDeletedTask(task, taskList.size());
        String toBeWritten = taskList.toString();
        storage.write(toBeWritten);
    }

    @Override
    public boolean isExit(){
        return false;
    }


}
