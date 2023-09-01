package fishron;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveTasksToFile(taskList.getList());
        ui.showGoodbyeMessage();
    }
}