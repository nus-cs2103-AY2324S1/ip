public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new DukeException("Invalid task index.");
        }

        try {
            Task taskToUnmark = taskList.getTasks().get(taskIndex);

            if (taskToUnmark.isDone) {
                taskToUnmark.isNotCompleted();
                ui.showTaskUnmarked(taskToUnmark);
                storage.saveTask(taskList.getTasks());
            } else {
                throw new DukeException("This task is already marked as done.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task index is out of range!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
