public class ListCommand extends Command {
    static final String COMMAND_WORD = "list";
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.getList());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
