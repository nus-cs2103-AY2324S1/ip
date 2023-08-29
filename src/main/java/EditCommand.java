public class EditCommand extends Command {
    private String editType;
    private int ind;

    public EditCommand(String editType, int ind) {
        this.editType = editType;
        this.ind = ind;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) {
        String action = tasks.editTask(editType, ind);
        ui.respondUser(action);
    }
    @Override
    public boolean isExit() {
        return false;
    };
}
