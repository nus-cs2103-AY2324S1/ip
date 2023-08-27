public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new ToDo(this.description);
        tasks.addTask(task);
        ui.printAddTask(task, tasks.getCountTasks());
    }
}
