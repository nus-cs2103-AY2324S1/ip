public class MarkCommand extends Command {
    int index;
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index >= taskList.size() || index < 0) {
            throw new DukeException("OOPS!!! Invalid task to be marked!");
        } else {
            taskList.mark(index);
            ui.sendMessage("OK, I've marked this task as done yet:\n\t\t" +
                    taskList.getPrint(index));
            storage.updateFileContents(taskList);
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
