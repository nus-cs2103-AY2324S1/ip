class ListCommand extends Command {
    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showMessage(taskList.toString());
    }
}
