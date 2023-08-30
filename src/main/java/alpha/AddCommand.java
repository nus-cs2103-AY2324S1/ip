package alpha;
public class AddCommand extends Command{

    private Task task;
    public AddCommand(TaskList taskList, FileHandler fh, UI ui, Task task) {
        super(taskList, fh, ui);
        super.isExit = false;
        this.task = task;
    }

    public void execute() {
        if (task != null) {
            this.taskList.add(task);
            fileHandler.saveToFile(task);
            ui.taskAdded(task, taskList.size());
        }
    }
}
