public class MarkCommand extends Command {
    int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        // Task number does not exist.
        try {
            if (this.index <= 0 || this.index >= taskList.size()) {
                throw new WrongNumberException("!!! Wrong Task Number Error !!!\n");
            }
            Task taskToMark = taskList.getTask(index);
            taskList.markTask(index);
            storage.saveToFile(taskList);
            ui.marksTasksMsg(taskToMark);
        } catch (WrongNumberException e) {
            ui.showInvalidTaskNumErr();
        }
    }
}
