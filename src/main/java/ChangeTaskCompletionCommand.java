public class ChangeTaskCompletionCommand extends Command {

    private final int inputIndex;
    private final boolean isCompleted;

    public ChangeTaskCompletionCommand(int inputIndex, boolean isCompleted) {
        this.inputIndex = inputIndex;
        this.isCompleted = isCompleted;
    }
    @Override
    public void execute(TaskList taskList, UserInterface ui, Storage storage) throws DuckyException {
        int updateIndex = this.inputIndex - 1;
        if (this.isCompleted) {
            Task changedTask = taskList.markTaskAsComplete(updateIndex);
            storage.save(taskList);
            ui.showMessagePerLine(
                    "Okay! I've marked this task as complete:",
                    changedTask.toString()
            );
        } else {
            Task changedTask = taskList.markTaskAsIncomplete(updateIndex);
            storage.save(taskList);
            ui.showMessagePerLine(
                    "Okay! I've marked this task as incomplete:",
                    changedTask.toString()
            );
        }
    }
}
