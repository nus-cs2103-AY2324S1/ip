public class ToDoCommand extends Command {
    String description;
    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        taskList.addToDo(description);
    }
}
