class ByeCommand extends Command {
    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.byeGreeting();
    }

    @Override
    boolean isExit() {
        return !super.isExit();
    }
}
