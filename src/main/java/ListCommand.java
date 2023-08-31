public class ListCommand extends Command {

    public ListCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException {
        ui.showList(tasks.taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
