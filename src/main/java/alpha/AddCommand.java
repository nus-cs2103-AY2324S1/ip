package alpha;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(TaskList taskList, FileHandler fh, UI ui, Task task) {
        super(taskList, fh, ui);
        isExit = false;
        this.task = task;
    }

    @Override
    public String execute() {
        if (task != null) {
            taskList.add(task);
            fileHandler.saveToFile(task);
            return ui.taskAdded(task, taskList.size());
        }
        return "";
    }

}
