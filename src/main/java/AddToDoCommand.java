public class AddToDoCommand extends Command {
    private String description;

    AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ToDo todo = new ToDo(description);
        taskList.add(todo);
        ui.addToListSuccess(todo, taskList.size());
        storage.saveList(taskList.getAllTasks());
    }
}
