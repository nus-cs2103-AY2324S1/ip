public class MarkCommand extends Command {
    private int taskIndexToMark;

    public MarkCommand(int taskIndexToMark) {
        this.taskIndexToMark = taskIndexToMark;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTaskAsDone(taskIndexToMark);
        ui.showMarked(tasks.getTask(taskIndexToMark));
        storage.saveTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }
}