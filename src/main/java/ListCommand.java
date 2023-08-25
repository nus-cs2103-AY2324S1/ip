public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws TaskNumberException {
        ui.botPrintTaskList(taskList);
    }
}
