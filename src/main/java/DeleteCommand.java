public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index >= taskList.size() || index < 0) {
            throw new DukeException("OOPS!!! Invalid index to be deleted!");
        } else {
            String deletedTask = taskList.getPrint(index);
            taskList.deleteTask(index);
            ui.sendMessage("Noted. I've removed this task:\n\t\t"
                    + deletedTask
                    + "\n\tNow you have " + taskList.size() + " tasks in the list.");
            storage.updateFileContents(taskList);
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
