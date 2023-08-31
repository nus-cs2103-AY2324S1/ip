
public class ListCommand extends Command{
    public ListCommand(String fullCommand) {
        super(fullCommand);
    }
    @Override
    public void execute(TaskList tasks ,Ui ui, Storage storage) {
                Ui.showLine();
                tasks.printTasks();
                Ui.showLine();
    }
    @Override
    public boolean isExit() {
        return false;
    }

}
