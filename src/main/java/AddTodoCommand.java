public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (description.trim().isEmpty()) {
                throw new EmptyDescriptionException("todo");
            }

            Todo newTodo = new Todo(description);
            taskList.add(newTodo);
            ui.showAdd(newTodo, taskList.getLength());
        } catch (EmptyDescriptionException e) {
            ui.showDukeException(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
