public class MarkCommand extends Command{
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void executeCommand(UI ui, Actions actionList) throws DukeException {
        Task resultingTask = actionList.unmark(taskNumber);
        if (resultingTask != null) {
            ui.lineSandwich(" Fine. Marked this task as done, better not change your mind:\n " + resultingTask);
        } else {
            ui.lineSandwich(" Task number does not exist, review input.");
        }
    }
}
