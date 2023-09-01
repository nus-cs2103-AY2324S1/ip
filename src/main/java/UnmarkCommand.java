public class UnmarkCommand extends Command{
    private final int num;

    public UnmarkCommand(int num){
        this.num = num;
    }
    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        tasksList.unMarkTask(num);
    }
}
