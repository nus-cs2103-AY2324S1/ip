public class DeadLineCommand extends Command {
    private String description;
    private String by;

    public DeadLineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        DeadLine deadline = new DeadLine(description, by);
        taskList.addTask(deadline);
        storage.saveTask(taskList.getTasks());
        ui.showTaskAdded(deadline, taskList.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
