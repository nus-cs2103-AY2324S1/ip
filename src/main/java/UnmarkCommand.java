public class UnmarkCommand extends Command {
    private int num;

    public UnmarkCommand(int num) {
        this.num = num;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.unmark(num);
        ui.printMarkMessage(task);
        String toBeWritten = taskList.toString();
        storage.write(toBeWritten);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
