public class ExitCommand extends Command {
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        storage.saveDataToFile(list.getList());
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }


}
