public class DeleteCommand extends Command {
    String fullCommand;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        taskList.deleteTask(fullCommand);
        storage.saveList(taskList);
    }

}
