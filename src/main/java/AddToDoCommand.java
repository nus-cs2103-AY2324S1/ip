public class AddToDoCommand extends Command {
    private String description;

    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException {
        ToDo t = tasks.addTodo(description);
        ui.sayAdd(tasks, t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
