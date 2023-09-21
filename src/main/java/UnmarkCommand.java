public class UnmarkCommand extends Command{
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void executeCommand(UI ui, Actions actionList) throws DukeException {
        Task resultingTask = actionList.mark(taskNumber);
        if (resultingTask != null) {
            ui.lineSandwich(" Well, you changed your mind :(. Just this once:\n " + resultingTask);
        } else {
            ui.lineSandwich(" Task number does not exist, review input.");
        }
    }
}
