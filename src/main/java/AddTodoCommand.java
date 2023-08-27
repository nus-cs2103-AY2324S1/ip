public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        tasks.addTask(new ToDo(description));
        ui.showTaskAddedMessage(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
