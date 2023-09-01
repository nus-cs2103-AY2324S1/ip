public class UnmarkCommand extends Command {
    int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        // Task number does not exist.
        try {
            if (this.index < 0 || this.index >= taskList.size()) {
                throw new WrongNumberException("!!! Wrong Task Number Error !!!\n");
            }
            Task taskToUnmark = taskList.getTask(index);
            taskList.unmarkTask(index);
            storage.saveToFile(taskList);
            ui.unmarksTasksMsg(taskToUnmark);
        } catch (WrongNumberException e) {
            ui.showInvalidTaskNumErr();
        }
    }
}
