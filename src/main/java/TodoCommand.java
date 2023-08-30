public class TodoCommand extends Command {

    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        Task temp = new Todo(description);
        taskList.addTask(temp);
        ui.printAddTaskMessage(temp, taskList);
        storage.save(taskList);
    }
}
