import java.io.IOException;

public class DeleteTaskCommand extends Command{
    private int index;

    /**
     * Constructor for the DeleteTaskCommand class.
     *
     * @param index Index of the task to be deleted.
     */
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = taskList.getTask(index);
            taskList.delete(index);
            storage.rewriteToFile(taskList.getList());
            ui.successfulTaskDeletionMsg(task.displayableForm(), taskList.getSize());
        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
