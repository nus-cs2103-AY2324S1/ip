public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("Listing...");
        ui.showTaskList(taskList);
    }
}
