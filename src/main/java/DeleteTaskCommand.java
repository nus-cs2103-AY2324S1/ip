/**
 * Command used to delete a Task from the TaskList
 */
public class DeleteTaskCommand extends Command {
    private final int idx;

    /**
     * Constructs a DeleteTaskCommand
     * @param idx Index of task to mark
     */
    public DeleteTaskCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task t = taskList.deleteTask(idx);
            ui.printToScreen("Noted. I've removed this task:\n" +
                    "\t" + t);
            ui.printToScreen(taskList.getCountString());
            storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            ui.showError(String.format("%d is not a valid index! Unable to delete task.", idx + 1));
        }
    }
}
