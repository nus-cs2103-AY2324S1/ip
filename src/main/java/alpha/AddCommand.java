package alpha;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(TaskList taskList, FileHandler fh, UI ui, Task task) {
        super(taskList, fh, ui);
        isExit = false;
        this.task = task;
    }

    @Override
    public void execute() {
        if (task != null) {
            taskList.add(task);
            fileHandler.saveToFile(task);
            ui.taskAdded(task, taskList.size());
        }
    }
}
