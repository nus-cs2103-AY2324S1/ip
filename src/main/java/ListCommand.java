public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, UserInterface ui, Storage storage) {
        ui.showMessagePerLine(taskList.getPrintableList());
    }
}
