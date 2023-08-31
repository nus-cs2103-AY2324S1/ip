public class DisplayTaskCommand extends Command {

    public DisplayTaskCommand() {

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }
}
