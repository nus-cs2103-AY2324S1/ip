public class AddTodoCommand implements Command {
    private String description;

    public AddTodoCommand(String input) throws SallyException {
        if (!input.isEmpty()) {
            this.description = input;
        } else {
            throw new SallyException("OOPS! The description of a todo cannot be empty.");
        }
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws SallyException {
        Todo newTodo = new Todo(description);
        tasks.addTask(newTodo);
        storage.saveTasksToFile(tasks);
        ui.showAddedTask(newTodo, tasks.getSize());
    }
}