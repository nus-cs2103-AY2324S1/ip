public class ToDoCommand extends Command {
    private Storage storage;
    private Todo todo;

    public ToDoCommand(TaskList taskList, Ui ui, Storage storage, String description) {
        super(taskList, ui);
        this.storage = storage;
        this.todo = new Todo(description);
    }

    @Override
    public void execute() {
        taskList.getTasks().add(todo);
        storage.saveTask(todo);
        ui.showTaskAdded(todo, taskList.getTasks().size());
    }
}
